package com.example.site.event;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EventApplication {

	@Value("${spring.application.name}")
	private String applicationName;

	public static void main(String[] args) {
		SpringApplication.run(EventApplication.class, args);
	}

}
