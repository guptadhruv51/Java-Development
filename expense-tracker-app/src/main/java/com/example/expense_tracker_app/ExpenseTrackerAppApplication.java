package com.example.expense_tracker_app;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(
		info = @Info(
				title = "Expense Tracker REST API documentation",
				description="Expense Tracker REST API documentation",
				version = "v1.0",
				contact = @Contact(
						name="Dhruv Gupta",
						email="guptadhruv51@gmail.com"
				)
		)
)
@SpringBootApplication
public class ExpenseTrackerAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExpenseTrackerAppApplication.class, args);
	}

}
