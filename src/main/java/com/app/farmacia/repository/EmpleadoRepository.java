package com.app.farmacia.repository;

import com.app.farmacia.entity.Empleado;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {
    Optional<Empleado> findByEmail(String email);

    @Query("SELECT e FROM Empleado e WHERE " +
            "(:nombre IS NULL OR e.nombre LIKE %:nombre%) AND " +
            "(:apellido IS NULL OR e.apellido LIKE %:apellido%) AND " +
            "(:puesto IS NULL OR e.puesto LIKE %:puesto%)")
    Page<Empleado> findByFilters(@Param("nombre") String nombre,
                                 @Param("apellido") String apellido,
                                 @Param("puesto") String puesto,
                                 Pageable pageable);
}
