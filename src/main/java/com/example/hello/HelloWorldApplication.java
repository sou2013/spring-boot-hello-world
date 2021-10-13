package com.example.hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class HelloWorldApplication {

	public static void main(String[] args) {

		SpringApplication.run(HelloWorldApplication.class, args);
    	System.out.println("\n\n*************************************************************************************");
    	System.out.println("***  This is a dummy backend service for validating JWT and checking user roles   ***");
    	System.out.println("*************************************************************************************\n\n");
	}
/*
	@Bean
	public WebMvcConfigurer configurer(){
		return new WebMvcConfigurer(){
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
						.allowedOrigins("*");
			}
		};
	}

 */
}