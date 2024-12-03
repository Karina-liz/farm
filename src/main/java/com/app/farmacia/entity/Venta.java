package com.app.farmacia.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Venta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String codigo;
    private LocalDateTime fechaVenta;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    private Integer tipoVenta;

    @ManyToOne
    @JoinColumn(name = "local_id")
    private Local local;
}
