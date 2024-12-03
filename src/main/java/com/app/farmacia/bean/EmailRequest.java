package com.app.farmacia.bean;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class EmailRequest {

    @Email(message = "Email.emailRequest.email")
    @NotEmpty(message = "NotEmpty.emailRequest.email")
    private String email;
}
