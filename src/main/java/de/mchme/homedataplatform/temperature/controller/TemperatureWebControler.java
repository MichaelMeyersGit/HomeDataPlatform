package de.mchme.homedataplatform.temperature.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TemperatureWebControler {

	 @RequestMapping("/temperature/index")
	 public String temperatureIndex(Model model) {
		 return "temperature/index";
	 } 
	
}
