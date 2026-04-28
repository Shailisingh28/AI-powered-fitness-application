package com.ActivityService.com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient

public class ComApplication {

	public static void main(String[] args) {
		SpringApplication.run(ComApplication.class, args);
	}

}
