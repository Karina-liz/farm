package com.app.farmacia.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SendGridDto {
    @Getter
    @Setter
    public static class ErrorResponse {
        private List<Error> errors;
    }

    @Getter
    @Setter
    public static class Error {
        private String message;
        private String field;
        private String from;
        private String help;
    }
}

