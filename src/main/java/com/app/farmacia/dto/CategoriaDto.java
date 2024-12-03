package com.app.farmacia.dto;

import com.app.farmacia.entity.Categoria;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.collections4.CollectionUtils;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CategoriaDto {
    @Getter
    @Setter
    public static class FilterRequest {
        @Parameter(in = ParameterIn.QUERY)
        private Integer index;
        @Parameter(in = ParameterIn.QUERY)
        private Integer size;
        @Parameter(in = ParameterIn.QUERY)
        private String nombre;
    }

    @Getter
    @Setter
    public static class Response {
        private Long id;
        private String nombre;

        public Response(Categoria data) {
            this.id = data.getId();
            this.nombre = data.getNombre();
        }

        public List<Response> list(List<Categoria> list) {
            List<Response> result = null;

            if (CollectionUtils.isNotEmpty(list)) {
                result = new ArrayList<>();

                for (Categoria data : list) {
                    result.add(new Response(data));
                }
            }

            return result;
        }
    }

    @Getter
    @Setter
    public static class Request {
        @NotBlank(message = "NotBlank.createCategory.nombre")
        private String nombre;
    }

    @Getter
    @Setter
    public static class Update {
        @NotNull
        private Long id;

        @NotBlank(message = "NotBlank.createCategory.nombre")
        private String nombre;
    }
}
