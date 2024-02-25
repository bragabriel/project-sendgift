package com.gabriel.sendgift;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories //indicando a lib -> Spring Data Mongo DB
public class SendgiftApplication {

	public static void main(String[] args) {
		SpringApplication.run(SendgiftApplication.class, args);
	}

}
