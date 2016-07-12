package de.mchme.homedataplatform.temperature.rules;

import java.util.Date;
import java.util.List;

import org.easyrules.annotation.Action;
import org.easyrules.annotation.Condition;
import org.easyrules.annotation.Rule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.mchme.homedataplatform.data.TemperatureData;
import de.mchme.homedataplatform.repositories.TemperatureRepository;
import de.mchme.homedataplatform.rules.BaseRule;
import de.mchme.homedataplatform.utils.RulesUtils;

/**
 * 
 * @author Michael Meyer
 * 
 *  has Threshhold already reached in a dedicated Intervall
 *
 */
@Rule(name = "threshholreached" )
public class TemperatureThresholdHasAlreadyReachedRule extends BaseRule {
	
private final static Logger logger = LoggerFactory.getLogger(TemperatureThresholdHasAlreadyReachedRule.class);
	
	private double threshhold ;
	
	/**
	 *  timespan in Minutes
	 */
	private int timespan ;
	
	private TemperatureRepository tempRepository ;
	
	public TemperatureThresholdHasAlreadyReachedRule(int timespan , double threshhold, TemperatureRepository tempRepository, int mynumber, int[] ruleslist) {
		super(mynumber, ruleslist) ;
		this.threshhold = threshhold ;
		this.timespan = timespan ;
		this.tempRepository = tempRepository ;
	}
	
	@Condition
    public boolean when() {

	 logger.debug("entering when");
		
	 boolean evaluate = false;
      
     Date startdate = RulesUtils.getTimespanDate(timespan);
     
     logger.debug(startdate.toString());
          
     List<TemperatureData> list = this.tempRepository.findByLogDateGreaterThanAndTemperatureGreaterThanEqual(startdate, threshhold);
     
     if(list.size() ==  0) {
    	 logger.debug("found no results, lets go on");
    	 evaluate = true;
     }
      
      return evaluate ;
    }
	
	@Action
	public void doSomething() {
		logger.debug("enetering the Action");
	}
	


}
