package com.natwestgroup.challenge.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("natwest")
public class ReceiverController {

	private static final Logger LOGGER = LogManager.getLogger(ReceiverController.class);
}
