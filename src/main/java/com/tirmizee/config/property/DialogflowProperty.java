package com.tirmizee.config.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "dialogflow")
public class DialogflowProperty {
    private String credentialsFile;
    private String projectId;
    private String agentId;
    private String languageCode;
}
