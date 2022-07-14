package com.sofka.apiciclismobackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
public class ApiCiclismoBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiCiclismoBackendApplication.class, args);
	}

}
