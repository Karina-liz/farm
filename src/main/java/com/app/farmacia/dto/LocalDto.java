package com.app.farmacia.dto;

import com.app.farmacia.entity.Local;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LocalDto {
    @Getter
    @Setter
    @NoArgsConstructor
    public static class Response {
        private Long id;
        private String local;
        private String direccion;
        private String ciudad;
        private String direccionCompleta;

        public Response(Local data) {
            this.id = data.getId();
            this.local = data.getLocal();
            this.direccion = data.getDireccion();
            this.ciudad = data.getCiudad();
            this.direccionCompleta = data.getLocal() + " - " + data.getDireccion();
        }

        public List<Response> list(List<Local> list) {
            List<Response> result = null;

            if (CollectionUtils.isNotEmpty(list)) {
                result = new ArrayList<>();

                for (Local data : list) {
                    result.add(new Response(data));
                }
            }

            return result;
        }
    }
}
