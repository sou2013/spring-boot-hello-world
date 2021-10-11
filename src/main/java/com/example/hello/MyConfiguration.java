package com.example.hello;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// @Configuration
public class MyConfiguration {

  //  @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
       //     @Override
            public void addCorsMappings(CorsRegistry registry) {
                //registry.addMapping("/api/**");
                registry.addMapping("/**").allowedOrigins("https://localhost:8443").allowedMethods("GET", "POST", "OPTIONS", "PUT")
                        .allowedHeaders("Content-Type", "X-Requested-With", "accept", "Origin", "Access-Control-Request-Method",
                                "Access-Control-Request-Headers, Content-Range, Content-Disposition, Content-Description")
                     //   .exposedHeaders("Access-Control-Allow-Origin", "Access-Control-Allow-Credentials")
                        .allowCredentials(true).maxAge(36000);
            }
        };
    }
}
