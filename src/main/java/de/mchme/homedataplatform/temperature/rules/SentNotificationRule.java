package de.mchme.homedataplatform.temperature.rules;

import org.easyrules.annotation.Action;
import org.easyrules.annotation.Condition;
import org.easyrules.annotation.Rule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.mchme.homedataplatform.notify.INotify;
import de.mchme.homedataplatform.rules.BaseRule;

@Rule(name = "sentnotification" )
public class SentNotificationRule extends BaseRule {
	
	private final static Logger logger = LoggerFactory.getLogger(SentNotificationRule.class);
	
	private INotify notify ;
	private String message ;
	private String event ;
	private String topic ;
	
	public SentNotificationRule(String message, String event, String topic ,INotify notify, int mynumber, int[] ruleslist) {
		super(mynumber, ruleslist);
		this.notify = notify ;
		this.event = event ;
		this.message = message ;
		this.topic = topic ;
	}
	
	
	@Condition
    public boolean when() {
		logger.debug("entering when");
		return true ;
    }
	
	@Action
	public void doSomething() {
		logger.debug("enetering the Action");
		this.notify.sentNotification(event, message, topic);
	}
	


}
