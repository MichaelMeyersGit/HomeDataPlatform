package de.mchme.homedataplatform.notify.android;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * 
 * @author Michael Meyer
 *
 * http://www.notifymyandroid.com/api.jsp
 * http://www.notifymyandroid.com/
 */
@Component
public class NotifyMyAndroid {
	
	
	private String apikey ;
	
	private String application ;
	
	/**
	 * https://www.notifymyandroid.com/publicapi/notify POST
	 */
	public void sentNotification(String event, String message) {
		
	}
	
	private RestTemplate getRestTemplate() {
		RestTemplate restTemplate = new RestTemplate();
		

		
		return restTemplate;
	}

}
