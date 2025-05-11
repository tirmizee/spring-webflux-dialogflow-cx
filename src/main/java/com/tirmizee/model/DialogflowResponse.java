package com.tirmizee.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class DialogflowResponse {
    private String userInput;
    private String matchedIntent;
    private List<String> replies = new ArrayList<>();
}
