package com.app.farmacia.bean;

import com.app.farmacia.dto.ProductoDto;
import com.app.farmacia.entity.Producto;
import lombok.Getter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
public class Carrito {
    private final List<CarritoItem> items = new ArrayList<>();

    public void agregarProducto(ProductoDto.Response producto, Long cantidad, Long userId) {
        for (CarritoItem item : items) {
            if (item.getProducto().getId().equals(producto.getId())) {
                item.setCantidad(item.getCantidad() + cantidad);
                return;
            }
        }
        items.add(new CarritoItem(producto, userId, cantidad));
    }

    public void eliminarProducto(Long productoId) {
        items.removeIf(item ->
                item.getProducto().getId().equals(productoId));
    }

    public BigDecimal getTotal() {
        return items.stream()
                .map(item ->
                        item.getProducto().getPrecioVenta()
                                .multiply(BigDecimal.valueOf(item.getCantidad())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}
