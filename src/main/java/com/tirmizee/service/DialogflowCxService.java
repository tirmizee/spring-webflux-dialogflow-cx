package com.tirmizee.service;

import com.google.cloud.dialogflow.cx.v3.*;
import com.tirmizee.config.property.DialogflowProperty;
import com.tirmizee.model.DialogflowResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Service
@RequiredArgsConstructor
public class DialogflowCxService {

    private final SessionsClient sessionsClient;
    private final DialogflowProperty dialogflowProperty;

    public Mono<DialogflowResponse> detectIntent(String userInput, String sessionId) {
        return Mono.fromCallable(() -> {

            SessionName session = SessionName.of(
                    dialogflowProperty.getProjectId(),
                    dialogflowProperty.getRegion(),
                    dialogflowProperty.getAgentId(),
                    sessionId
            );

            TextInput textInput = TextInput.newBuilder()
                    .setText(userInput)
                    .build();

            QueryInput queryInput = QueryInput.newBuilder()
                    .setText(textInput)
                    .setLanguageCode("th-TH")
                    .build();

            DetectIntentRequest request = DetectIntentRequest.newBuilder()
                    .setSession(session.toString())
                    .setQueryInput(queryInput)
                    .build();

            DetectIntentResponse response = sessionsClient.detectIntent(request);
            return toResponse(response.getQueryResult());
        }).subscribeOn(Schedulers.boundedElastic()); // ✅ Run in non-blocking background thread
    }

    public static DialogflowResponse toResponse(QueryResult result) {

        DialogflowResponse response = new DialogflowResponse();

        // ข้อความที่ผู้ใช้พิมพ์มา
        response.setUserInput(result.getText());

        // Intent ที่ match
        response.setMatchedIntent(result.getIntent().getDisplayName());

        // ข้อความที่ Dialogflow ตอบกลับ
        for (ResponseMessage msg : result.getResponseMessagesList()) {
            if (msg.hasText()) {
                response.getReplies().addAll(msg.getText().getTextList());
            }
        }

        return response;
    }

}
