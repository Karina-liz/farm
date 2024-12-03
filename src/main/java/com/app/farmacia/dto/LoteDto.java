package com.app.farmacia.dto;

import com.app.farmacia.entity.Lote;
import com.app.farmacia.util.Util;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.collections4.CollectionUtils;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LoteDto {
    @Getter
    @Setter
    @NoArgsConstructor
    public static class Response {
        private Long id;
        private String lote;
        private String fechaVencimiento;
        private String loteCompleto;

        public Response(Lote data) {
            this.id = data.getId();
            this.lote = data.getLote();
            this.fechaVencimiento = Util.localDateToString(data.getFechaVencimiento());
            this.loteCompleto = this.lote + " - " + this.fechaVencimiento;
        }

        public List<LoteDto.Response> list(List<Lote> list) {
            List<LoteDto.Response> result = null;

            if (CollectionUtils.isNotEmpty(list)) {
                result = new ArrayList<>();

                for (Lote data : list) {
                    result.add(new LoteDto.Response(data));
                }
            }

            return result;
        }
    }
}
