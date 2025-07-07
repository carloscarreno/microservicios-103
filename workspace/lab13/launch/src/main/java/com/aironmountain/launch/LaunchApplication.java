package com.aironmountain.launch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class LaunchApplication {

	public static void main(String[] args) {
		SpringApplication.run(LaunchApplication.class, args);
	}

}
