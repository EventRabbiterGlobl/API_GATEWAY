package com.API_GATEWAY.API_GATEWAY.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {


    @Bean
    public RestTemplate template(){
        return   new RestTemplate();
    }
}
