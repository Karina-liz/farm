package com.app.farmacia.dto;

import com.app.farmacia.entity.User;
import com.app.farmacia.util.Util;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import jakarta.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.collections4.CollectionUtils;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UsuarioDto {

    @Getter
    @Setter
    public static class FilterRequest {
        @Parameter(in = ParameterIn.QUERY)
        private Integer index;
        @Parameter(in = ParameterIn.QUERY)
        private Integer size;
        @Parameter(in = ParameterIn.QUERY)
        private String nombre;
        @Parameter(in = ParameterIn.QUERY)
        private String apellido;
        @Parameter(in = ParameterIn.QUERY)
        private String username;
    }

    @Getter
    @Setter
    public static class Response {
        private Long id;
        private String nombre;
        private String apellido;
        private String correo;
        private String dni;
        private String telefono;
        private String direccion;
        private String fechaRegistro;

        public Response(User data) {
            this.id = data.getId();
            this.nombre = data.getNombre();
            this.apellido = data.getApellido();
            this.correo = data.getUsername();
            this.dni = data.getDni();
            this.telefono = data.getTelefono();
            this.direccion = data.getDireccion();
            this.fechaRegistro = Util.localDateTimeToString(data.getFechaRegistro());
        }

        public List<Response> list(List<User> list) {
            List<Response> result = null;

            if (CollectionUtils.isNotEmpty(list)) {
                result = new ArrayList<>();

                for (User data : list) {
                    result.add(new Response(data));
                }
            }

            return result;
        }
    }

    @Getter
    @Setter
    public static class Request {
        @Email(message = "Email.emailRequest.email")
        @NotBlank(message = "El campo username es requerido")
        private String username;
        @NotBlank(message = "El campo password es requerido")
        private String password;
        private List<String> roles;
        @NotBlank(message = "El campo nombre es requerido")
        private String nombre;
        @NotBlank(message = "El campo apellido es requerido")
        private String apellido;
        @NotBlank(message = "El campo dni es requerido")
        @Pattern(regexp = "^[0-9]{8}$", message = "El DNI debe tener 8 dígitos numéricos")
        private String dni;
        @NotBlank(message = "El campo telefono es requerido")
        @Pattern(regexp = "^[0-9]{9}$", message = "El Telefono debe tener 9 dígitos numéricos")
        private String telefono;
        @NotBlank(message = "El campo direccion es requerido")
        private String direccion;
    }

    @Getter
    @Setter
    public static class Update {
        @Email(message = "Email.emailRequest.email")
        @NotBlank(message = "El campo username es requerido")
        private String username;
        @NotBlank(message = "El campo password es requerido")
        private String password;
        private List<String> roles;
        @NotBlank(message = "El campo nombre es requerido")
        private String nombre;
        @NotBlank(message = "El campo apellido es requerido")
        private String apellido;
        @NotBlank(message = "El campo dni es requerido")
        private String dni;
        @NotBlank(message = "El campo telefono es requerido")
        private String telefono;
        @NotBlank(message = "El campo direccion es requerido")
        private String direccion;
    }
}
