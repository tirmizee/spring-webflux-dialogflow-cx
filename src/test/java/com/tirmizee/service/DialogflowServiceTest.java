package com.tirmizee.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DialogflowServiceTest {

    @Autowired
    private DialogflowService dialogflowService;

    @Test
    void testDetectIntent() throws Exception {
        String sessionId = "test-session-id";
        String userInput = "สวัสดี";

        String response = dialogflowService.detectIntent(userInput, sessionId)
                .doOnError(Throwable::printStackTrace)
                .block();

        assertNotNull(response);
        System.out.println("✅ Dialogflow Response:\n" + response);
    }

}