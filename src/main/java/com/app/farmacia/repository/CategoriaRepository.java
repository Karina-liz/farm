package com.app.farmacia.repository;

import com.app.farmacia.entity.Categoria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    @Query("SELECT c FROM Categoria c WHERE " +
            "(:nombre IS NULL OR c.nombre LIKE %:nombre%)")
    Page<Categoria> findByNombreCustom(@Param("nombre") String nombre, Pageable pageable);
}
