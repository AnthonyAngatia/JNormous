package com.communication.RestTemplateDemo;

import com.communication.RestTemplateDemo.util.RestTemplateErrorHandler;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@SpringBootApplication
public class RestTemplateDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestTemplateDemoApplication.class, args);
    }

// Method 1
//	@Bean
//	public RestTemplate getRestTemplate(){
//		return new RestTemplate();
//	}

    @Bean
    public RestTemplate getRestTemplate(RestTemplateBuilder builder) {
        return builder
                .errorHandler(new RestTemplateErrorHandler())
                .setConnectTimeout(Duration.ofMillis(3000))
                .setReadTimeout(Duration.ofMillis(3000))
                .build();
    }

    @Bean
    public ModelMapper getModelMapper(){
        return new ModelMapper();
    }

}
