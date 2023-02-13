package com.amano.moeconn.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "mail.qq")
public class QQMailProperties {
    private String secretKey;
    private String smtpHost;
    private String sendMail;
    private String auth;
}
