package de.mchme.homedataplatform.controler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import de.mchme.homedataplatform.data.TemperatureData;
import de.mchme.homedataplatform.excel.ExcelView;
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
	

	/**
	 * will export the requested Temp Values to Excel
	 * 
	 * @param identifier
	 * @param startdate
	 * @param enddate
	 * @param request
	 * @param response
	 * @return
	 */
    @RequestMapping(value="/temperature/export", method=RequestMethod.POST)
	public ModelAndView exportTemperatureToExcel(
			@RequestBody TemperatureExportBody exportBody,
			HttpServletRequest request, 
			HttpServletResponse response) {
    	
    	logger.debug("entering export");
    	
    	List<TemperatureData> templist = this.temperatureRepo.findByIdentifierAndLogDateBetween(exportBody.getIdentifier(), exportBody.getStartdate(), exportBody.getEnddate()) ;
    	
    	Map<String, Object> model = new HashMap<String, Object>();
        //Sheet Name
        model.put("sheetname", "TestSheetName");
        //Headers List
        List<String> headers = new ArrayList<String>();
        headers.add("Date");
        headers.add("Identifier");
        headers.add("Temperature");
        headers.add("Unit");
        model.put("headers", headers);
        
        //Results Table (List<Object[]>)
        
        List<List<String>> results = new ArrayList<List<String>>();
        
        for(TemperatureData data : templist) {
        	 List<String> myrow = new ArrayList<String>();
        	 myrow.add(data.getLogDate().toString());
        	 myrow.add(data.getIdentifier().toString());
        	 myrow.add(data.getTemperature().toString());
        	 myrow.add(Character.toString(data.getUnit()));
        	 results.add(myrow);
        }
        
        model.put("results",results);
        
        response.setContentType( "application/ms-excel" );
        response.setHeader( "Content-disposition", "attachment; filename=myfile.xls" );
        if(results.size() > 0) {
        	response.setStatus(HttpStatus.OK.value());
        } else {
        	response.setStatus(HttpStatus.NO_CONTENT.value());
        }
        
        logger.debug("leaving export");
        
        
        return new ModelAndView(new ExcelView(), model);
		
    	
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
