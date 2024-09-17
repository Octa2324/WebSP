package com.backend.backApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
@SpringBootApplication
public class BackAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackAppApplication.class, args);
	}

}
