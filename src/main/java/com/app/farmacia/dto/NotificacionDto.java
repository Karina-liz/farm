package com.app.farmacia.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class NotificacionDto {
    @Getter
    @Setter
    public static class Request {
        private String nombre;
        private String apellido;
        private String username;
        private String password;
    }
}
