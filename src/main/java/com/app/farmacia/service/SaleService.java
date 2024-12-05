package com.app.farmacia.service;

import com.app.farmacia.bean.Carrito;
import com.app.farmacia.dto.VentaDto;

public interface SaleService {
    VentaDto.Response registrarVenta(Carrito carrito);
}
