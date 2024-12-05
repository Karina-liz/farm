package com.app.farmacia.service.impl;

import com.app.farmacia.bean.Carrito;
import com.app.farmacia.bean.CarritoItem;
import com.app.farmacia.dto.ProductoDto;
import com.app.farmacia.dto.VentaDto;
import com.app.farmacia.entity.*;
import com.app.farmacia.repository.*;
import com.app.farmacia.service.SaleService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class SaleServiceImpl implements SaleService {
    private final VentaRepository ventaRepository;
    private final VentaProductoRepository ventaProductoRepository;
    private final BoletaRepository boletaRepository;
    private final ProductoRepository productoRepository;
    private final LocalRepository localRepository;
    private final UserRepository userRepository;
    private final PagoRepository pagoRepository;

    @Override
    public VentaDto.Response registrarVenta(Carrito carrito) {
        log.info("Usuario Id {}", carrito.getItems().getFirst().getUserId());
        User user = userRepository.findById(carrito.getItems().getFirst().getUserId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        
        Local local = localRepository.findById(1L)
                .orElseThrow(() -> new RuntimeException("Local no encontrado"));

        Venta venta = Venta.builder()
                .codigo("V-" + System.currentTimeMillis())
                .user(user)
                .tipoVenta(1)
                .fechaVenta(LocalDateTime.now())
                .local(local)
                .build();
        venta = ventaRepository.save(venta);

        for (CarritoItem item : carrito.getItems()) {
            Producto producto = productoRepository.findById(item.getProducto().getId())
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
            VentaProducto ventaProducto = VentaProducto.builder()
                    .venta(venta)
                    .producto(producto)
                    .build();
            ventaProductoRepository.save(ventaProducto);
        }

        Pago pago = pagoRepository.findById(1L)
                .orElseThrow(() -> new RuntimeException("Pago no encontrado"));

        Boleta boleta = Boleta.builder()
                .codigo("B-" + System.currentTimeMillis())
                .costoTotal(carrito.getTotal())
                .venta(venta)
                .pago(pago)
                .build();
        boletaRepository.save(boleta);

        return new VentaDto.Response(venta);
    }
}
