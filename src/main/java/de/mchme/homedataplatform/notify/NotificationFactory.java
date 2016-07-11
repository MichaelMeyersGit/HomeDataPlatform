package de.mchme.homedataplatform.notify;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 
 * @author Michael Meyer
 * 
 * Factory for returning the selected Notification Implementation
 *
 */
@Component
public class NotificationFactory {
	
	@Value("${notification.system:mock}")
	private String notificationsystem ;
	
	@Autowired
	@Qualifier("mocknotify")
	private INotify mock ;
	
	@Autowired
	@Qualifier("gcm")
	private INotify gcm ;
	
	@Autowired
	@Qualifier("nma")
	private INotify nma ;
	
	
	public INotify getNotificationSystem() {
		
		INotify notify = null ;
		
		if(notificationsystem.equals("mock")) {
			notify = mock ;
		} else if (notificationsystem.equals("gcm")) {
			notify = gcm ;
		} else if (notificationsystem.equals("nma")) {
			notify = nma ;
		}
		
		return notify ;
		
	}
	

}
