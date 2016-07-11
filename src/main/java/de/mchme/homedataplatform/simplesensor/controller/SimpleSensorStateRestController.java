package de.mchme.homedataplatform.simplesensor.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import de.mchme.homedataplatform.data.SimpleSensorStateData;

@RestController
public class SimpleSensorStateRestController {
	
	private final static Logger logger = LoggerFactory.getLogger(SimpleSensorStateRestController.class);
		
	public static final String ADD = "/simplestate/add" ;
	
	@Autowired
	private SimpleSensorStateHandler handler ;
	
	@RequestMapping(value = ADD, method = RequestMethod.POST)
	public ResponseEntity<String> addState(@RequestBody SimpleSensorStateData data) 	 throws Exception {
		
		logger.debug("entering addState");
		
		ResponseEntity<String> response = handler.addState(data) ; 
		
		return response ;
		
	}

}
