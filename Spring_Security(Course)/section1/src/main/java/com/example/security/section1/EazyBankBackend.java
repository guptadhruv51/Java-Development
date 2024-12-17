package com.example.security.section1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.example.security.section1.Repository")
@EntityScan("com.example.security.section1.Model")
public class EazyBankBackend{

	public static void main(String[] args) {
		SpringApplication.run(EazyBankBackend.class, args);
	}

}

