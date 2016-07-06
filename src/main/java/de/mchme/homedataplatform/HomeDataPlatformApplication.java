package de.mchme.homedataplatform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
public class HomeDataPlatformApplication {

	public static void main(String[] args) {
		SpringApplication.run(HomeDataPlatformApplication.class, args);
	}
}
