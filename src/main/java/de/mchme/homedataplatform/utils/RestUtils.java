package de.mchme.homedataplatform.utils;

import java.io.IOException;

import org.slf4j.Logger;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

/**
 * 
 * @author Michael Meyer
 *
 */
public class RestUtils {
	
	public static final String JSON_DATE_FORMAT = "yyyy-MM-dd HH:mm"; 
	
	public static RestTemplate getRestTemplate(Logger logger) {
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
