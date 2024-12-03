package com.app.farmacia.dto;

import com.app.farmacia.entity.Empleado;
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
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EmpleadoDto {
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
        private String puesto;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class Response {
        private Long id;
        private String nombre;
        private String apellido;
        private String email;
        private String telefono;
        private String puesto;
        private String dni;
        private BigDecimal salario;
        private LocalDto.Response local;

        public Response(Empleado data) {
            this.id = data.getId();
            this.nombre = data.getNombre();
            this.apellido = data.getApellido();
            this.email = data.getEmail();
            this.telefono = data.getTelefono();
            this.puesto = data.getPuesto();
            this.dni = data.getDni();
            this.salario = data.getSalario();
            this.local = new LocalDto.Response(data.getLocal());
        }

        public List<Response> list(List<Empleado> list) {
            List<Response> result = null;

            if (CollectionUtils.isNotEmpty(list)) {
                result = new ArrayList<>();

                for (Empleado data : list) {
                    result.add(new Response(data));
                }
            }

            return result;
        }
    }

    @Getter
    @Setter
    public static class Request {
        @NotBlank(message = "El campo nombre es requerido")
        private String nombre;
        @NotBlank(message = "El campo apellido es requerido")
        private String apellido;
        @Email(message = "Email.emailRequest.email")
        @NotBlank(message = "El campo email es requerido")
        private String email;
        @NotBlank(message = "El campo puesto es requerido")
        private String puesto;
        @NotBlank(message = "El campo dni es requerido")
        @Pattern(regexp = "^[0-9]{8}$", message = "El DNI debe tener 8 dígitos numéricos")
        private String dni;
        @NotBlank(message = "El campo telefono es requerido")
        @Pattern(regexp = "^[0-9]{9}$", message = "El Telefono debe tener 9 dígitos numéricos")
        private String telefono;
        @NotNull(message = "El campo salario es requerido")
        private BigDecimal salario;
        @NotNull(message = "El campo local es requerido")
        private Long localId;
    }

    @Getter
    @Setter
    public static class Update {
        @NotBlank(message = "El campo nombre es requerido")
        private String nombre;
        @NotBlank(message = "El campo apellido es requerido")
        private String apellido;
        @Email(message = "Email.emailRequest.email")
        @NotBlank(message = "El campo email es requerido")
        private String email;
        @NotBlank(message = "El campo puesto es requerido")
        private String puesto;
        @NotBlank(message = "El campo dni es requerido")
        @Pattern(regexp = "^[0-9]{8}$", message = "El DNI debe tener 8 dígitos numéricos")
        private String dni;
        @NotBlank(message = "El campo telefono es requerido")
        @Pattern(regexp = "^[0-9]{9}$", message = "El Telefono debe tener 9 dígitos numéricos")
        private String telefono;
        @NotNull(message = "El campo salario es requerido")
        private BigDecimal salario;
        @NotNull(message = "El campo local es requerido")
        private Long localId;
    }
}
