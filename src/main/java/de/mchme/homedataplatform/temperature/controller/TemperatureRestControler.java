package de.mchme.homedataplatform.temperature.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import de.mchme.homedataplatform.data.TemperatureData;


@RestController
public class TemperatureRestControler {
	
	private final static Logger logger = LoggerFactory.getLogger(TemperatureRestControler.class);
	
	@Autowired
	private TemperatureHandler tempHandler ;
	
	public final static String ADDLIST = "/temperature/addlist";
	public final static String ADDSINGLE = "/temperature/addsingle";

	/**
	 * will add new Temp Values to the database
	 * 
	 * @param temperatureList
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = ADDLIST, method = RequestMethod.POST)
	public ResponseEntity<String> addTemperatureList(@RequestBody List<TemperatureData> temperatureList) 	 throws Exception {
		
		logger.debug("entering addTemperatureList");
		
		ResponseEntity<String> response = this.tempHandler.addTemperatureList(temperatureList);

		logger.debug("leaving addTemperatureList");
		
		return response ;		
		
	}
	
	@RequestMapping(value = ADDSINGLE, method = RequestMethod.POST)
	public ResponseEntity<String> addSingleTemperature(@RequestBody TemperatureData temperature) 	 throws Exception {
		
		logger.debug("entering addSingleTemperature");
		
		List<TemperatureData> list = new ArrayList<TemperatureData>();
		list.add(temperature);
		
		ResponseEntity<String> response = this.tempHandler.addTemperatureList(list);

		logger.debug("leaving addSingleTemperature");
		
		return response ;
		
	}
	



	
	
}
