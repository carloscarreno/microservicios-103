package com.aironmountain.bitcoins;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class BitcoinsApplication {

	public static void main(String[] args) {
		SpringApplication.run(BitcoinsApplication.class, args);
	}

}
