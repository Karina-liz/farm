package com.app.farmacia.config;

import com.app.farmacia.bean.SendgridProperties;
import com.sendgrid.SendGrid;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class SendgridConfig {

    private final SendgridProperties sendgridProperties;

    @Bean
    public SendGrid getSendGrid() {
        return new SendGrid(sendgridProperties.getKey());
    }
}
