package com.app.farmacia.dto;

import com.app.farmacia.entity.Producto;
import com.app.farmacia.util.Util;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.collections4.CollectionUtils;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductoDto {
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
        private Long categoria;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class Response {
        private Long id;
        private CategoriaDto.Response categoria;
        private BigDecimal precioCompra;
        private BigDecimal precioVenta;
        private String principioActivo;
        private String precentacion;
        private String laboratorio;
        private String foto;
        private LoteDto.Response lote;
        private String fechaVencimiento;

        public Response(Producto data) {
            this.id = data.getId();
            this.categoria = new CategoriaDto.Response(data.getCategoria());
            this.precioCompra = data.getPrecioCompra();
            this.precioVenta = data.getPrecioVenta();
            this.principioActivo = data.getPrincipioActivo();
            this.precentacion = data.getPrecentacion();
            this.laboratorio = data.getLaboratorio();
            this.foto = data.getFoto();
            this.lote = new LoteDto.Response(data.getLote());
            this.fechaVencimiento = Util.localDateToString(data.getFechaVencimento());
        }

        public List<Response> list(java.util.List<Producto> list) {
            List<ProductoDto.Response> result = null;

            if (CollectionUtils.isNotEmpty(list)) {
                result = new ArrayList<>();

                for (Producto data : list) {
                    result.add(new ProductoDto.Response(data));
                }
            }

            return result;
        }
    }

    @Getter
    @Setter
    public static class Request {
        @NotNull(message = "El campo nombreProducto es requerido")
        private String nombreProducto;
        @NotNull(message = "El campo categoria es requerido")
        private Long categoriaId;
        @NotNull(message = "El campo precioCompra es requerido")
        private BigDecimal precioCompra;
        @NotNull(message = "El campo precioVenta es requerido")
        private BigDecimal precioVenta;
        @NotBlank(message = "El campo principioActivo es requerido")
        private String principioActivo;
        @NotBlank(message = "El campo precentacion es requerido")
        private String precentacion;
        @NotBlank(message = "El campo laboratorio es requerido")
        private String laboratorio;
        private String foto;
        @NotNull(message = "El campo lote es requerido")
        private Long loteId;
        private LocalDate fechaVencimiento;
    }

    @Getter
    @Setter
    public static class Update {
        @NotNull(message = "El campo nombreProducto es requerido")
        private String nombreProducto;
        @NotNull(message = "El campo categoria es requerido")
        private Long categoriaId;
        @NotNull(message = "El campo precioCompra es requerido")
        private BigDecimal precioCompra;
        @NotNull(message = "El campo precioVenta es requerido")
        private BigDecimal precioVenta;
        @NotBlank(message = "El campo principioActivo es requerido")
        private String principioActivo;
        @NotBlank(message = "El campo precentacion es requerido")
        private String precentacion;
        @NotBlank(message = "El campo laboratorio es requerido")
        private String laboratorio;
        private String foto;
        @NotNull(message = "El campo lote es requerido")
        private Long loteId;
        private LocalDate fechaVencimiento;
    }
}
