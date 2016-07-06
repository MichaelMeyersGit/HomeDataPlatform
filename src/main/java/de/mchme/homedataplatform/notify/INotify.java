package de.mchme.homedataplatform.notify;

import org.springframework.stereotype.Component;

/**
 * 
 * @author Michael Meyer
 * 
 * this Interface should be used to be autowired, so you can decide which implmentation of Notification
 * Service you will use for your application
 *
 */
@Component
public interface INotify {
	
	public void sentNotification(String event, String message, String topic) ;

}
