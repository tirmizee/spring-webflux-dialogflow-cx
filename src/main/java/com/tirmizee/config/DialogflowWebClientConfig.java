package com.tirmizee.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

import static com.google.common.net.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Configuration
public class DialogflowWebClientConfig {

    @Bean
    public WebClient dialogflowWebClient() throws Exception {
        return WebClient.builder()
                .baseUrl("https://asia-southeast1-dialogflow.googleapis.com")
                .defaultHeader(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                .build();
    }

}
