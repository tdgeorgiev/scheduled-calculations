package com.example.scheduled_calculations;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class ScheduledCalculationsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ScheduledCalculationsApplication.class, args);
	}

}
