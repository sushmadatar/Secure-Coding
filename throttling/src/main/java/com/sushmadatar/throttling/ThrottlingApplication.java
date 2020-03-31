package com.sushmadatar.throttling;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ThrottlingApplication {

	public static void main(String[] args) {
		SpringApplication.run(ThrottlingApplication.class, args);
		// System.setProperty("server.lockOutTime","20");
	}
}
