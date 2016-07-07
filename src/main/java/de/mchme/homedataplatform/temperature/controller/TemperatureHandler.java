package de.mchme.homedataplatform.temperature.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import de.mchme.homedataplatform.data.TemperatureData;
import de.mchme.homedataplatform.repositories.TemperatureRepository;
import de.mchme.homedataplatform.temperature.rules.TemperatureRulesComponent;
import de.mchme.homedataplatform.units.TemperatureUnits;

@Component
public class TemperatureHandler {

	private final static Logger logger = LoggerFactory.getLogger(TemperatureHandler.class);
	
	
	@Autowired
	private TemperatureRepository temperatureRepo ;
	
	@Autowired
	private TemperatureRulesComponent rulesComponent ;
	
		
	public ResponseEntity<String> addTemperatureList(List <TemperatureData> temperatureList) 	 throws Exception {
		
		logger.debug("entering addTemperature");
		
		HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
		
		boolean isValid = isValid(temperatureList);
				
		if(isValid) {
			this.temperatureRepo.save(temperatureList);
			httpStatus = HttpStatus.OK;		
			this.executeRules(temperatureList);
		}
		
		logger.debug("leaving addTemperature");
		
		return new ResponseEntity<>(httpStatus);
	}
	
	
	private boolean isValid(List<TemperatureData> temperatureList) {
		boolean isValid = true;
		
		Date now = Calendar.getInstance().getTime();
				
		for(TemperatureData temp : temperatureList) {
			if(!TemperatureUnits.isValid(temp.getUnit())) {
				isValid = false;
				break;
			} else if (temp.getLogDate() == null) {
				temp.setLogDate(now);
			} else if (temp.getIdentifier() == null) {
				isValid = false;
				break;
			} else if (temp.getTemperature() == null) {
				isValid = false;
				break;
			} else if(!TemperatureUnits.isInValidRange(temp.getUnit(), temp.getTemperature())) {
				isValid = false;
				break;
			}
		}
		
		
		return isValid ;
		
	}
	
	private void executeRules(List <TemperatureData> temperatureList) {
		this.rulesComponent.executeRules(temperatureList);
	}

}
