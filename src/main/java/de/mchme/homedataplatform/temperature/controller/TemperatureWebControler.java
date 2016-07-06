package de.mchme.homedataplatform.temperature.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import de.mchme.homedataplatform.data.TemperatureData;
import de.mchme.homedataplatform.excel.ExcelView;
import de.mchme.homedataplatform.repositories.TemperatureRepository;

@Controller
public class TemperatureWebControler {
	
	private final static Logger logger = LoggerFactory.getLogger(TemperatureWebControler.class);
	
	@Autowired
	private TemperatureRepository temperatureRepo ;


	 @RequestMapping("/temperature/index")
	 public String temperatureIndex(Model model) {
		 
		 Date now = Calendar.getInstance().getTime();
		 TemperatureExportBody export = new TemperatureExportBody();
		 export.setEnddate(now);
		 export.setStartdate(now);
		 export.setIdentifier(1);
		 
		 model.addAttribute("temperatureExportBody" , export) ;
		 return "temperature/index";
	 } 
	 
	@RequestMapping(value = "/temperature/excelexport", method = RequestMethod.POST)
	public ModelAndView exportTemperatureToExcel(@ModelAttribute TemperatureExportBody tempExportBody, Model model,
			HttpServletRequest request, HttpServletResponse response) {

		if (logger.isDebugEnabled()) {
			logger.debug("entering export");
			logger.debug("Identifier " + tempExportBody.getIdentifier());
			logger.debug("Startdate " + tempExportBody.getStartdate());
			logger.debug("Enddate " + tempExportBody.getEnddate());
		}

		List<TemperatureData> templist = this.temperatureRepo.findByIdentifierAndLogDateBetween(
				tempExportBody.getIdentifier(), tempExportBody.getStartdate(), tempExportBody.getEnddate());

		logger.debug("Got " + templist.size() + " results");

		Map<String, Object> excelmodel = new HashMap<String, Object>();
		// Sheet Name
		excelmodel.put("sheetname", "TestSheetName");
		// Headers List
		List<String> headers = new ArrayList<String>();
		headers.add("Date");
		headers.add("Identifier");
		headers.add("Temperature");
		headers.add("Unit");
		excelmodel.put("headers", headers);

		// Results Table (List<Object[]>)

		List<List<String>> results = new ArrayList<List<String>>();

		for (TemperatureData data : templist) {
			List<String> myrow = new ArrayList<String>();
			myrow.add(data.getLogDate().toString());
			myrow.add(data.getIdentifier().toString());
			myrow.add(data.getTemperature().toString());
			myrow.add(Character.toString(data.getUnit()));
			results.add(myrow);
		}

		excelmodel.put("results", results);

		response.setContentType("application/ms-excel");
		response.setHeader("Content-disposition", "attachment; filename=export.xls");
		if (results.size() > 0) {
			response.setStatus(HttpStatus.OK.value());
		} else {
			response.setStatus(HttpStatus.NO_CONTENT.value());
		}

		logger.debug("leaving export");

		return new ModelAndView(new ExcelView(), excelmodel);

	}
	
}
