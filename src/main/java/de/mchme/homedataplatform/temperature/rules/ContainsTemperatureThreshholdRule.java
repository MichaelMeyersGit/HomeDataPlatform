package de.mchme.homedataplatform.temperature.rules;

import java.util.List;

import org.easyrules.annotation.Action;
import org.easyrules.annotation.Condition;
import org.easyrules.annotation.Priority;
import org.easyrules.annotation.Rule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.mchme.homedataplatform.data.TemperatureData;
import de.mchme.homedataplatform.utils.RulesUtils;


@Rule(name = "threshholdrule" )
public class ContainsTemperatureThreshholdRule {
	
	private final static Logger logger = LoggerFactory.getLogger(ContainsTemperatureThreshholdRule.class);
	
	private List<TemperatureData> tempList ;
	
	private double threshhold ;
	
	public ContainsTemperatureThreshholdRule(List<TemperatureData> tempList, double threshhold) {
		this.tempList = tempList ;
		this.threshhold = threshhold ;
	}
	
	@Condition
    public boolean when() {

	 logger.debug("entering when");
		
	  boolean evaluate = false;
      
      for(TemperatureData dt : this.tempList) {
    	  if(dt.getTemperature() >= this.threshhold) {
    		  logger.debug("need to evaluate: " + dt.getTemperature() + " threshhold: " + this.threshhold);
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
	
	@Priority
	public int getPriority() {
		return RulesUtils.FIRST;
	}

	


	
	

	
}
