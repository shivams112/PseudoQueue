package com.natwestgroup.challenge.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.natwestgroup.challenge.message.Response;
import com.natwestgroup.challenge.message.TrxnRequestBody;
import com.natwestgroup.challenge.service.PseudoQueueService;

@RestController
@RequestMapping("pseudoqueue")
public class ReceiverController {

	private static final Logger LOGGER = LogManager.getLogger(ReceiverController.class);
	
	@Autowired
	private PseudoQueueService mPseudoQueueService;
	
	
	/* POST API to accept 
	 * data from user 
	*/
	@RequestMapping(value = "/receiver", method = RequestMethod.POST, produces = "application/json")
	public Response receiveTransaction(@RequestBody TrxnRequestBody request){
		LOGGER.info("receiveTransaction(): controller called, trxn="+request);
		return mPseudoQueueService.processReceiverRequest(request);
	}
}
