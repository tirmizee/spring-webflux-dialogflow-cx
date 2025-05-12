package com.tirmizee.utils;

import com.google.cloud.dialogflow.cx.v3.QueryResult;
import com.google.cloud.dialogflow.cx.v3.ResponseMessage;
import com.tirmizee.model.DialogflowResponse;

public class MapperUtils {

    public static DialogflowResponse toResponse(QueryResult result) {
        DialogflowResponse response = new DialogflowResponse();

        response.setUserInput(result.getText());

        response.setMatchedIntent(result.getIntent().getDisplayName());


        for (ResponseMessage msg : result.getResponseMessagesList()) {
            if (msg.hasText()) {
                response.getReplies().addAll(msg.getText().getTextList());
            }
        }

        return response;
    }

}
