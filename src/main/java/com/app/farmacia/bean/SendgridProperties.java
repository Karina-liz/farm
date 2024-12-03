package com.app.farmacia.bean;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "sendgrid")
@Component
@Getter
@Setter
public class SendgridProperties {
    private String key;
    private String emisorNombre;
    private String emisorCorreo;
    private Template template;
}
