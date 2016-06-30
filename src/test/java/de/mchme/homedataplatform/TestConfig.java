package de.mchme.homedataplatform;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@Configuration
public class TestConfig {
	
@Autowired private WebApplicationContext context;
	
	private MockMvc mvc;

	
	@Bean
	MockMvc mvc() {
		context.getServletContext()
			.setAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE, context);
		mvc = MockMvcBuilders.webAppContextSetup(context).build();
		return mvc;
	}

}
