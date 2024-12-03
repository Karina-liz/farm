package com.app.farmacia.bean;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Getter
@Setter
public class ClientRequest {
    @NotBlank(message = "NotBlank.createClient.username")
    private String username;
    @NotBlank(message = "NotBlank.createClient.password")
    private String password;
    @NotEmpty(message = "NotEmpty.createClient.roles")
    private List<String> roles;
    private String nombre;
    private String apellido;
    private String dni;
    private String telefono;
    private String direccion;
}
