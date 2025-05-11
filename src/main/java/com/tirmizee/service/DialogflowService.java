package com.tirmizee.service;

import com.google.api.gax.core.CredentialsProvider;
import com.google.auth.oauth2.GoogleCredentials;
import com.tirmizee.config.property.DialogflowProperty;
import com.tirmizee.model.DetectIntentRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class DialogflowService {

    private final CredentialsProvider credentialsProvider;
    private final DialogflowProperty dialogflowProperty;
    private final WebClient dialogflowWebClient;

    public String getAccessToken() throws Exception {
        GoogleCredentials googleCredentials = (GoogleCredentials) credentialsProvider.getCredentials();
        googleCredentials.refreshIfExpired();
        return googleCredentials.getAccessToken().getTokenValue();
    }

    public Mono<String> detectIntent(String text, String sessionId) throws Exception {
        String endpoint = String.format("/v3/projects/%s/locations/%s/agents/%s/sessions/%s:detectIntent",
                dialogflowProperty.getProjectId(), "asia-southeast1", dialogflowProperty.getAgentId(), sessionId
        );

        DetectIntentRequest payload = DetectIntentRequest.from(text, "th-TH");

        return dialogflowWebClient.post()
                .uri(endpoint)
                .header("Authorization", "Bearer " + getAccessToken())
                .bodyValue(payload)
                .retrieve()
                .onStatus(HttpStatusCode::isError, response -> response.bodyToMono(String.class)
                        .flatMap(body -> {
                            System.err.println("❌ Error Body: " + body);
                            return Mono.error(new RuntimeException("Bad Request: " + body));
                        }))
                .bodyToMono(String.class);
    }

}
