package com.example.apiintegrationdemo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class ApiIntegrationDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiIntegrationDemoApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(RestClient.Builder builder) {
		return args -> {
			var client = builder
					.baseUrl("https://jsonplaceholder.typicode.com")
					.build();

			ResponseEntity<User> entity = client.get()
					.uri("/users/1")
					.retrieve()
					.toEntity(User.class);

			System.out.println(entity.getBody());

		};
	}

	@Bean
	RestClient restClient(RestClient.Builder builder) {
		return builder
				.baseUrl("https://jsonplaceholder.typicode.com")
				.build();
	}

}
