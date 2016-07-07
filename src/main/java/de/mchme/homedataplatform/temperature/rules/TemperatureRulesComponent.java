package de.mchme.homedataplatform.temperature.rules;

import java.util.List;

import org.easyrules.api.RulesEngine;
import org.easyrules.core.RulesEngineBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import de.mchme.homedataplatform.data.TemperatureData;

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
	
	private RulesEngine rulesEngine ;
		
	
	public TemperatureRulesComponent() {
		logger.debug("entering TemperatureRulesComponent");
		this.rulesEngine = RulesEngineBuilder.aNewRulesEngine().withSkipOnFirstAppliedRule(true).withSkipOnFirstFailedRule(true).build();		
	}
	
	public void executeRules(List<TemperatureData> temperatureList) {
		logger.debug("entering execute Rules");
		this.rulesEngine.clearRules();
		
		ContainsTemperatureThreshholdRule r1 = new ContainsTemperatureThreshholdRule();
		r1.setTempList(temperatureList);
		
		this.rulesEngine.registerRule(r1);
		
		this.rulesEngine.fireRules();
		
		logger.debug("leaving execute Rules");
	}

	
	
}
