package com.example.requestscheduler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class RequestSchedulerApplication {

	public static void main(String[] args) {
		SpringApplication.run(RequestSchedulerApplication.class, args);
	}

}
