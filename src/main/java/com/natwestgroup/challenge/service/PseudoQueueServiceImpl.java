package com.natwestgroup.challenge.service;

import java.util.Arrays;
import java.util.Base64;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.natwestgroup.challenge.dao.TransactionRepository;
import com.natwestgroup.challenge.entities.Transaction;
import com.natwestgroup.challenge.message.Response;
import com.natwestgroup.challenge.message.TrxnRequestBody;

@Component
public class PseudoQueueServiceImpl implements PseudoQueueService {
	private static final Logger LOGGER = LogManager.getLogger(PseudoQueueServiceImpl.class);
	
	@Value("${receiver.uri:#{null}}")
	private String URI;
	
	@Autowired
	private TransactionRepository mTransactionRepository;

	@Override
	public Response processSenderRequest(TrxnRequestBody request) {
		LOGGER.info("processSenderRequest(): Enter");
		Response res = new Response();
		res.setResultCode(1);
		res.setResult("Fail");
		TrxnRequestBody encryptedReq = null;
		
		try {
			
			LOGGER.info("processSenderRequest(): Encrypting the request");
			encryptedReq = new TrxnRequestBody();
			encryptedReq.setAccountNumber(encodeString(request.getAccountNumber()));
			encryptedReq.setAccountFrom(encodeString(request.getAccountFrom()));
			encryptedReq.setAmount(encodeString(request.getAmount()));
			encryptedReq.setType(encodeString(request.getType()));
			encryptedReq.setCurrency(encodeString(request.getCurrency()));
			
			LOGGER.info("processSenderRequest(): Data Encrypted successfully, going to hit Receiver API==> URL: "+URI);
			RestTemplate restTemplate = new RestTemplate();
		    try {
		    	Response response = restTemplate.postForObject(URI, encryptedReq, Response.class);
			      
			      LOGGER.info("processSenderRequest(): Receiver API response= "+response);
			      if(response.getResultCode() ==0 ) {
						res.setResult("success");
						res.setResultCode(0);
			      }
                
		    } catch (Exception e) {
		    	LOGGER.info("processSenderRequest(): API Exception: "+e);
		    	e.printStackTrace();
		    }
						
		} catch (Exception e) {
			LOGGER.error("processSenderRequest() exception " + e);
		} finally {
			LOGGER.info("processSenderRequest(): Exit");
		}
		
		return res;
	}

	@Override
	public Response processReceiverRequest(TrxnRequestBody request) {
		LOGGER.info("processReceiverRequest(): Enter");
		Response res = new Response();
		res.setResultCode(1);
		res.setResult("Fail");
		Transaction transaction = null;
        try {
        	
        	LOGGER.info("processReceiverRequest(): Decrypting the request");
			transaction = new Transaction();
			transaction.setAccountNumber(decodeString(request.getAccountNumber()));
			transaction.setAccountFrom(decodeString(request.getAccountFrom()));
			transaction.setAmount(decodeString(request.getAmount()));
			transaction.setType(decodeString(request.getType()));
			transaction.setCurrency(decodeString(request.getCurrency()));
			LOGGER.info("processReceiverRequest(): Decrypting success, going to save in DB");
			mTransactionRepository.save(transaction);
			res.setResult("success");
			res.setResultCode(0);
        	
        } catch (Exception e) {
        	LOGGER.error("processReceiverRequest() exception " + e);
        	
        } finally {
        	LOGGER.info("processReceiverRequest(): Exit");
        }
		return res;
	}
	
	
	//Encrypt and Decrypt Methods
	
    private String encodeString(String data) {
    	if(data==null)
    		return null;
    	return Base64.getEncoder().encodeToString(data.getBytes());
    }
    
    private String decodeString(String data) {
    	if(data==null)
    		return null;
    	byte[] decodedBytes = Base64.getDecoder().decode(data);
    	String decodedString = new String(decodedBytes);
    	return decodedString;
    }
}
