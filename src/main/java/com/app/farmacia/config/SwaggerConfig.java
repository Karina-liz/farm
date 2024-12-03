package com.app.farmacia.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI openApiConfig() {
        return new OpenAPI()
                .info(apiDetails());
    }

    private Info apiDetails() {
        return new Info()
                .title("Farmacia Service")
                .description("Servicio para Farmacia.")
                .version("1.0.0")
                .contact(new Contact()
                        .name("Chomy rojas")
                        .email("tucorreo"));
    }
}
