package com.kone.services.iot.assignment.application;

import com.cloudant.client.api.CloudantClient;
import com.cloudant.client.api.Database;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
/**
 * Main Spring Boot Application class for IOT Assignment application
 * 
 */
@SpringBootApplication
@ComponentScan("com.kone.*")
public class EquipmentInformationApplication {

	public static void main(String[] args) {
		SpringApplication.run(EquipmentInformationApplication.class, args);
	}

	@Value("${cloudant.db}")
	private String cloudantDB;
	
	@Bean
	public Database mydb(CloudantClient cloudant) {
		return cloudant.database(cloudantDB, true);
	}
}
