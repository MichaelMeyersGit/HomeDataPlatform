package de.mchme.homedataplatform.temperature.rules;

import java.util.List;

import org.easyrules.api.RulesEngine;
import org.easyrules.core.RulesEngineBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import de.mchme.homedataplatform.data.TemperatureData;
import de.mchme.homedataplatform.notify.NotificationFactory;
import de.mchme.homedataplatform.repositories.TemperatureRepository;

/**
 * 
 * @author Michael Meyer
 * 
 * http://www.easyrules.org
 *
 */
@Component
public class TemperatureRulesComponent {
	
	private final static Logger logger = LoggerFactory.getLogger(TemperatureRulesComponent.class);
	
	@Value("${temperature.rules.threshhold:20.0}")
	private double threshhold ;
	
	@Value("${temperature.rules.timespan:60}")
	private int timespan ;
	
	@Autowired
	private TemperatureRepository tempRepository ;
	
	@Autowired
	private NotificationFactory notifyFactory ;
	
	
	private static final int CONTAINS_THRESHOLD_RULE = 10 ;
	private static final int TEMP_HAS_ALREADY_REACHED_RULE = 20 ;
	private static final int SENT_NOTIFICATION_RULE = 30 ;
	
	private static final int[] RULES_LIST = { CONTAINS_THRESHOLD_RULE ,  TEMP_HAS_ALREADY_REACHED_RULE , SENT_NOTIFICATION_RULE} ;
	

	
	private RulesEngine rulesEngine ;
		
	
	public TemperatureRulesComponent() {
		logger.debug("entering TemperatureRulesComponent");
		this.rulesEngine = RulesEngineBuilder.aNewRulesEngine().withSkipOnFirstAppliedRule(false).withSkipOnFirstFailedRule(true).build();		
	}
	
	public void executeRules(List<TemperatureData> temperatureList) {
		logger.debug("entering execute Rules");
		this.rulesEngine.clearRules();
		
		ContainsTemperatureThreshholdRule r1 = new ContainsTemperatureThreshholdRule(temperatureList, this.threshhold, CONTAINS_THRESHOLD_RULE , RULES_LIST);
		this.rulesEngine.registerRule(r1);
		
		TemperatureThresholdHasAlreadyReachedRule r2 = new TemperatureThresholdHasAlreadyReachedRule( timespan , threshhold,  tempRepository, TEMP_HAS_ALREADY_REACHED_RULE , RULES_LIST);
		this.rulesEngine.registerRule(r2);
		
		SentNotificationRule r3 = new SentNotificationRule("message" , "event" , "topic" , this.notifyFactory.getNotificationSystem(), SENT_NOTIFICATION_RULE, RULES_LIST);
		this.rulesEngine.registerRule(r3);
				
		
		this.rulesEngine.fireRules();
		
		logger.debug("leaving execute Rules");
	}

	
	
}
