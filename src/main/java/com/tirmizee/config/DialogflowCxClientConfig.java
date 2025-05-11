package com.tirmizee.config;

import com.google.api.gax.core.CredentialsProvider;
import com.google.cloud.dialogflow.cx.v3.SessionsClient;
import com.google.cloud.dialogflow.cx.v3.SessionsSettings;
import com.tirmizee.config.property.DialogflowProperty;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@RequiredArgsConstructor
@Configuration
public class DialogflowCxClientConfig {

    private final DialogflowProperty dialogflowProperty;

    @Bean
    public SessionsClient sessionsClient(CredentialsProvider credentialsProvider) throws IOException {
        SessionsSettings settings = SessionsSettings.newBuilder()
                .setCredentialsProvider(credentialsProvider)
                .setEndpoint(dialogflowProperty.getRegion() + "-dialogflow.googleapis.com:443")
                .build();
        return SessionsClient.create(settings);
    }

}
