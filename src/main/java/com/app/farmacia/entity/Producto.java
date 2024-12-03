package com.app.farmacia.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombreProducto;
    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;
    private BigDecimal precioCompra;
    private BigDecimal precioVenta;
    private String principioActivo;
    private String precentacion;
    private String laboratorio;
    private String foto;
    @ManyToOne
    @JoinColumn(name = "lote_id")
    private Lote lote;
    private LocalDate fechaVencimento;
}
