package com.app.farmacia.bean;

import com.app.farmacia.constaint.PasswordMatches;
import com.app.farmacia.validation.ValidationNotEmpty;
import com.app.farmacia.validation.ValidationPasswordMatches;
import com.app.farmacia.validation.ValidationSize;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
@PasswordMatches(groups = ValidationPasswordMatches.class)
public class UpdatePasswordRequest {

    @Size(min = 6, message = "Size.updatePasswordRequest.newPassword.regexp", groups = ValidationSize.class)
    @NotEmpty(message = "NotEmpty.updatePasswordRequest.newPassword", groups = ValidationNotEmpty.class)
    private String newPassword;

    @NotEmpty(message = "NotEmpty.updatePasswordRequest.confirmPassword", groups = ValidationNotEmpty.class)
    private String confirmPassword;
}
