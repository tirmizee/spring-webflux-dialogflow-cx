package com.tirmizee.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean
    public WebClient dialogflowWebClient() throws Exception {
        return WebClient.builder()
                .baseUrl("https://asia-southeast1-dialogflow.googleapis.com")
                .defaultHeader("Content-Type", "application/json")
                .build();
    }

}
