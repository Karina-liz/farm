package com.app.farmacia.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Constants {
    public static final String SUCCESS_MESSAGE = "Notificación enviada.";
    public static final String ERROR_MESSAGE = "Error al enviar notificación";
    public static final String SENGRID_ERROR_RESPONSE = "SendGrid body: {}";
    public static final String EXCEPTION_MESSAGE = "Exception: {}";
    public static final String ZONE = "-05:00";
    public static final String NOMBRE = "NOMBRE";
    public static final String APELLIDO = "APELLIDO";
    public static final String USERNAME = "USERNAME";
    public static final String PASSWORD = "PASSWORD";
    public static final String ROLE_ADMIN = "ROLE_ADMIN";
    public static final String ROLE_USER = "ROLE_USER";
    public static final String ROLE_EMPLOYEE = "ROLE_EMPLOYEE";
}
