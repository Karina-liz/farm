package com.app.farmacia.repository;

import com.app.farmacia.entity.Boleta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BoletaRepository extends JpaRepository<Boleta, Long> {
    Optional<Boleta> findByVentaId(Long ventaId);
}
