package com.tirmizee.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DetectIntentRequest {

    private QueryInput queryInput;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class QueryInput {
        private TextInput text;
        private String languageCode;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TextInput {
        private String text;
    }

    public static DetectIntentRequest from(String userInput, String langCode) {
        return new DetectIntentRequest(new QueryInput(new TextInput(userInput), langCode));
    }

}
