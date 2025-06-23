package com.igp.saludo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

	
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class SaludoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SaludoApplication.class, args);
	}

}
