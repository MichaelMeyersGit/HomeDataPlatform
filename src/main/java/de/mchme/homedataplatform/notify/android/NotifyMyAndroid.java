package de.mchme.homedataplatform.notify.android;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
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
@Component
public class NotifyMyAndroid implements INotify {
	
	private final static Logger logger = LoggerFactory.getLogger(NotifyMyAndroid.class);
	
	@Value("${nma.apikey:}")
	private String apikey ;
	
	@Value("${nma.application:}")
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
	@Async
	public void sentNotification(String event, String message, String topic) {
		
		MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		map.add("apikey", this.apikey);
		map.add("application", this.application);
		map.add("event", event);
		map.add("description", message);
		
		RestTemplate rest = RestUtils.getRestTemplate(logger);
		
		String result = rest.postForObject(NMA_NOTIFY, map, String.class);
		
		logger.debug(result);
		
	}
	
/*	private RestTemplate getRestTemplate() {
		RestTemplate restTemplate = new RestTemplate();
		
		ResponseErrorHandler responseErrorHandler = new DefaultResponseErrorHandler() {
			@Override
			public void handleError(ClientHttpResponse response) throws IOException {
				logger.warn("response status: " + response.getStatusCode() + " - response header: " + response.getHeaders());
			}
		};
		restTemplate.setErrorHandler(responseErrorHandler);
		
		
		return restTemplate;
	} */

}
