package de.mchme.homedataplatform.temperature.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import de.mchme.homedataplatform.data.TemperatureData;
import de.mchme.homedataplatform.repositories.TemperatureRepository;
import de.mchme.homedataplatform.units.TemperatureUnits;


@RestController
public class TemperatureRestControler {
	
	private final static Logger logger = LoggerFactory.getLogger(TemperatureRestControler.class);
	
	@Autowired
	private TemperatureRepository temperatureRepo ;

	/**
	 * will add new Temp Values to the database
	 * 
	 * @param temperatureList
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/temperature/add", method = RequestMethod.POST)
	public ResponseEntity<String> addTemperature(
			@RequestBody List<TemperatureData> temperatureList) 	 throws Exception {
		
		logger.debug("entering addTemperature");
		
		HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
		
		boolean isValid = isValid(temperatureList);
				
		if(isValid) {
			this.temperatureRepo.save(temperatureList);
			httpStatus = HttpStatus.OK;		
		}
		
		logger.debug("leaving addTemperature");
		
		return new ResponseEntity<>(httpStatus);
	}
		
	
	private boolean isValid(List<TemperatureData> temperatureList) {
		boolean isValid = true;
				
		for(TemperatureData temp : temperatureList) {
			if(!TemperatureUnits.isValid(temp.getUnit())) {
				isValid = false;
				break;
			} else if (temp.getLogDate() == null) {
				isValid = false;
				break;
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
	
	
}
