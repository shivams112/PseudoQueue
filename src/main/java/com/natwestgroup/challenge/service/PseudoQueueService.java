package com.natwestgroup.challenge.service;


import com.natwestgroup.challenge.message.TrxnRequestBody;
import com.natwestgroup.challenge.message.Response;

public interface PseudoQueueService {
	
	public Response processSenderRequest(TrxnRequestBody request);
	public Response processReceiverRequest(TrxnRequestBody request);
}
