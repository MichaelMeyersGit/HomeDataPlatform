package de.mchme.homedataplatform.notify.android;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import de.mchme.homedataplatform.notify.INotify;
import de.mchme.homedataplatform.utils.RestUtils;


/**
 * 
 * @author Michael Meyer
 *
 * Service for using Google Cloud Messaging Service
 * 
 * https://developers.google.com/cloud-messaging/topic-messaging#sending_topic_messages_from_the_server
 */
@Component
public class GCMNotification implements INotify {

private final static Logger logger = LoggerFactory.getLogger(GCMNotification.class);
	
	@Value("${gcm.apikey:}")
	private String apikey ;
	
	@Value("${gcm.serverurl:}")
	private String serverurl ;
	
	
	@Override
	@Async
	public void sentNotification(String event, String message, String topic) {
		logger.debug("entering sentNotification");
		
		
		NotifyData notify = new NotifyData();
		notify.setTo(topic);
		
		MessageData md = new MessageData();
		md.setMessage(message);
		
		notify.setData(md);
		
		if(logger.isDebugEnabled()) {
			GsonBuilder gsonBuilder = new GsonBuilder();
			Gson gson = gsonBuilder.create();
			String json = gson.toJson(notify);
			logger.debug(json);
		}
		
		RestTemplate rest = RestUtils.getRestTemplate(logger);
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add("Authorization", apikey);
		
		HttpEntity<NotifyData> entity = new HttpEntity<NotifyData>( notify , headers);
		
		logger.debug("sending to " + serverurl);
		
		ResponseEntity<String> responseEntity = rest.exchange(serverurl, HttpMethod.POST, entity, String.class);
		
		if(responseEntity.getStatusCode() != HttpStatus.OK) {
			logger.warn("received Status Code " +  responseEntity.getStatusCode() + " from GCM");
		}
		
		logger.debug("leaving sentNotification");
		
	}
	
	
	class NotifyData {
		
		private String to ;
		
		private MessageData data ;

		public String getTo() {
			return to;
		}

		public void setTo(String to) {
			this.to = to;
		}

		public MessageData getData() {
			return data;
		}

		public void setData(MessageData data) {
			this.data = data;
		}		
		
	}
	
	
	class MessageData {
		
		private String message ;

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}		
		
	}

	
	
	
	

}
