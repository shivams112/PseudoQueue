package com.natwestgroup.challenge.service;

import java.util.Base64;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import com.natwestgroup.challenge.message.Response;
import com.natwestgroup.challenge.message.TrxnRequestBody;

@Component
public class PseudoQueueServiceImpl implements PseudoQueueService {
	private static final Logger LOGGER = LogManager.getLogger(PseudoQueueServiceImpl.class);

	@Override
	public Response processSenderRequest(TrxnRequestBody request) {
		LOGGER.info("processSenderRequest(): Enter");
		Response res = new Response();
		res.setResultCode(1);
		res.setResult("Fail");
		
		try {
			
			LOGGER.info("processSenderRequest(): Account Num before: "+request.getAccountNumber());
			request.setAccountNumber(encodeString(request.getAccountNumber()));
			LOGGER.info("processSenderRequest(): Account Num after: "+request.getAccountNumber());
			
		} catch (Exception e) {
			LOGGER.error("processSenderRequest() exception e");
		} finally {
			LOGGER.info("processSenderRequest(): Exit");
		}
		
		return res;
	}

	@Override
	public Response processReceiverRequest(TrxnRequestBody request) {
		// TODO Auto-generated method stub
		return null;
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
