package com.app.farmacia.dto;

import com.app.farmacia.entity.Venta;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.collections4.CollectionUtils;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class VentaDto {
    @Getter
    @Setter
    @NoArgsConstructor
    public static class Response {
        private Long id;
        private String codigo;
        private LocalDateTime fechaVenta;
        private UsuarioDto.Response user;
        private Integer tipoVenta;
        private LocalDto.Response local;

        public Response(Venta data) {
            this.id = data.getId();
            this.codigo = data.getCodigo();
            this.fechaVenta = data.getFechaVenta();
            this.user = new UsuarioDto.Response(data.getUser());
            this.tipoVenta = data.getTipoVenta();
            this.local = new LocalDto.Response(data.getLocal());
        }

        public List<Response> list(List<Venta> list) {
            List<VentaDto.Response> result = null;

            if (CollectionUtils.isNotEmpty(list)) {
                result = new ArrayList<>();

                for (Venta data : list) {
                    result.add(new VentaDto.Response(data));
                }
            }

            return result;
        }
    }
}
