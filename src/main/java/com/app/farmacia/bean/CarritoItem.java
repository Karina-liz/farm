package com.app.farmacia.bean;

import com.app.farmacia.dto.ProductoDto;
import com.app.farmacia.entity.Producto;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CarritoItem {
    private ProductoDto.Response producto;
    private Long userId;
    private Long cantidad;
}
