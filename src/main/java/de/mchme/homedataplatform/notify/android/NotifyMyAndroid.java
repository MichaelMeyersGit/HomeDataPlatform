package de.mchme.homedataplatform.notify.android;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import de.mchme.homedataplatform.notify.INotify;
import de.mchme.homedataplatform.utils.RestUtils;



/**
 * 
 * @author Michael Meyer
 *
 * http://www.notifymyandroid.com/api.jsp
 * http://www.notifymyandroid.com/
 * 
 * Notify My Android is a simple Solution to send Notifications to an Android Device without
 * writing you own Android App
 * 
 */
@Component("nma")
public class NotifyMyAndroid implements INotify {
	
	private final static Logger logger = LoggerFactory.getLogger(NotifyMyAndroid.class);
	
	@Value("${nma.apikey:xxx}")
	private String apikey ;
	
	@Value("${nma.application:xxx}")
	private String application ;
	
	private final String NMA_NOTIFY  = "https://www.notifymyandroid.com/publicapi/notify" ;
	
	/**
	 * will sent a simple Notification to the Notify My Android Server
	 * 
	 * the Method were tagged as Async to avoid blocking the program
	 * 
	 * @param event
	 * @param message
	 */
	@Override
//	@Async
	public void sentNotification(String event, String message, String topic) {
		
		logger.debug("entering sentNotification for NMA");
		
		MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		map.add("apikey", this.apikey);
		map.add("application", this.application);
		map.add("event", event);
		map.add("description", message);
		
		logger.debug("filled MulitValueMap");
		
		RestTemplate rest = RestUtils.getRestTemplate(logger);
		
		logger.debug("got a Rest Template ... starting to call");
		
		String result = rest.postForObject(NMA_NOTIFY, map, String.class);
		
		logger.debug(result);
		
	}
	


}
