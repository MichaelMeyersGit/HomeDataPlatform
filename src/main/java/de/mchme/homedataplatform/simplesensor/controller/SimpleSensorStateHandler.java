package de.mchme.homedataplatform.simplesensor.controller;

import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import de.mchme.homedataplatform.data.SimpleSensorStateData;
import de.mchme.homedataplatform.repositories.SimpleSensorStateRepository;
import de.mchme.homedataplatform.utils.SensorStateUtils;

@Component
public class SimpleSensorStateHandler {
	
	private final static Logger logger = LoggerFactory.getLogger(SimpleSensorStateHandler.class);
	
	@Autowired
	private SimpleSensorStateRepository stateRepo ;
	
	public ResponseEntity<String> addState(SimpleSensorStateData data) 	 throws Exception {
	
		logger.debug("entering addState");
		
		HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
		
		if(data.getIdentifier() != null) {
		
			int state = SensorStateUtils.getSensorState(data.getState());
			data.setState(state);
		
			if(data.getLogDate() == null) {
				Date now = Calendar.getInstance().getTime();
				data.setLogDate(now);
			}
			
			stateRepo.save(data) ;
			
			httpStatus = HttpStatus.OK ;		
		}
		
		logger.debug("leaving addState");
		
		return new ResponseEntity<>(httpStatus);
	}
	
	
	

}
