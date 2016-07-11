package de.mchme.homedataplatform.notify;

import java.net.URI;
import java.net.URISyntaxException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import de.mchme.homedataplatform.utils.RestUtils;

@Component("mocknotify")
public class MockNotificationImplementation implements INotify {
	
	private final static Logger logger = LoggerFactory.getLogger(MockNotificationImplementation.class);


	@Override
	public void sentNotification(String event, String message, String topic) {
		logger.warn("calling the Notification Mock");
		
		RestTemplate rest = RestUtils.getRestTemplate(logger) ;
		
		try {
			ResponseEntity<String> response = rest.getForEntity(new URI("http://ip.jsontest.com") , String.class);
			logger.info(Integer.toString(response.getStatusCode().value()));
		} catch (RestClientException | URISyntaxException e) {
			logger.error(e.getMessage());
		}
	
	}

}
