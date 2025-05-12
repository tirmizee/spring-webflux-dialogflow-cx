package com.tirmizee.service;

import com.tirmizee.model.DialogflowResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;


@SpringBootTest
class DialogflowCxServiceTest {

    @Autowired
    private DialogflowCxService dialogflowCxService;

    @Test
    void testDetectIntent_withRealDialogflow() {
        String input = "สวัสดี";
        String sessionId = "test-" + System.currentTimeMillis();

        Mono<DialogflowResponse> resultMono = dialogflowCxService.detectIntent(input, sessionId);

        StepVerifier.create(resultMono)
                .expectNextMatches(result -> {
                    System.out.println("✅ Response: " + result);
                    return !result.getReplies().isEmpty();
                })
                .verifyComplete();
    }

}