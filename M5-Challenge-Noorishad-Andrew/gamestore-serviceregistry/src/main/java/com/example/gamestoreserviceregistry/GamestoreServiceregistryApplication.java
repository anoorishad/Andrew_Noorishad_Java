package com.example.gamestoreserviceregistry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class GamestoreServiceregistryApplication {

	public static void main(String[] args) {
		SpringApplication.run(GamestoreServiceregistryApplication.class, args);
	}

}
