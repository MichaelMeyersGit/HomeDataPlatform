package de.mchme.homedataplatform.temperature.rules;

import java.util.List;

import org.easyrules.annotation.Action;
import org.easyrules.annotation.Condition;
import org.easyrules.annotation.Rule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.mchme.homedataplatform.data.TemperatureData;


@Rule(name = "threshholdrule" )
public class ContainsTemperatureThreshholdRule {
	
	private final static Logger logger = LoggerFactory.getLogger(ContainsTemperatureThreshholdRule.class);
	
	private List<TemperatureData> tempList ;
	
	private double threshold ;
	
	@Condition
    public boolean when() {

	 logger.debug("entering when");
		
	  boolean evaluate = false;
      
      for(TemperatureData dt : this.tempList) {
    	  if(dt.getTemperature() >= this.threshold) {
    		  logger.debug("need to evaluate");
    		  evaluate = true ;
    		  break ;
    	  }
      }
      
      
      return evaluate ;
    }
	
	@Action
	public void doSomething() {
		logger.debug("enetering the Action");
	}

	public List<TemperatureData> getTempList() {
		return tempList;
	}

	public void setTempList(List<TemperatureData> tempList) {
		this.tempList = tempList;
	}

	public double getThreshold() {
		return threshold;
	}

	public void setThreshold(double threshold) {
		this.threshold = threshold;
	}
	
	

	
}
