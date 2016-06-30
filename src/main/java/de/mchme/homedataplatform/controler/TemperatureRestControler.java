package de.mchme.homedataplatform.controler;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import de.mchme.homedataplatform.data.TemperatureData;


@RestController
public class TemperatureRestControler {
	
	

	@RequestMapping(value = "/temperature/add", method = RequestMethod.POST)
	public ResponseEntity<String> addTemperature(
			@RequestBody List<TemperatureData> temperatureList) 	 throws Exception {

	
		HttpStatus httpStatus =HttpStatus.OK;
		return new ResponseEntity<>(httpStatus);
	}
	
	
}
