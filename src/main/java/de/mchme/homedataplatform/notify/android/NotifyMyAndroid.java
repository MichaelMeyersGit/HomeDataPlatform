package de.mchme.homedataplatform.notify.android;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.ResponseErrorHandler;
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
	
	private final static Logger logger = LoggerFactory.getLogger(NotifyMyAndroid.class);
	
	@Value("${nma.apikey:}")
	private String apikey ;
	
	@Value("${nma.application:}")
	private String application ;
	
	/**
	 * https://www.notifymyandroid.com/publicapi/notify POST
	 */
	public void sentNotification(String event, String message) {
		
	}
	
	private RestTemplate getRestTemplate() {
		RestTemplate restTemplate = new RestTemplate();
		
		ResponseErrorHandler responseErrorHandler = new DefaultResponseErrorHandler() {
			@Override
			public void handleError(ClientHttpResponse response) throws IOException {
				logger.warn("response status: " + response.getStatusCode() + " - response header: " + response.getHeaders());
			}
		};
		restTemplate.setErrorHandler(responseErrorHandler);
		
		
		return restTemplate;
	}

}
