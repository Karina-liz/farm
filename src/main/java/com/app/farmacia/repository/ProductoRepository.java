package com.app.farmacia.repository;

import com.app.farmacia.entity.Categoria;
import com.app.farmacia.entity.Producto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
    List<Producto> findByCategoria(Categoria categoria);

    @Query("SELECT p FROM Producto p WHERE " +
            "LOWER(p.nombreProducto) LIKE LOWER(CONCAT('%', :nombreProducto, '%'))")
    List<Producto> findByNombreProductoContainingIgnoreCase(String nombreProducto);

    List<Producto> findByCategoriaNombreContainingIgnoreCase(String categoria);

    @Query("SELECT p FROM Producto p WHERE " +
            "(:nombre IS NULL OR p.nombreProducto LIKE %:nombre%) AND " +
            "(:categoria IS NULL OR p.categoria.id = :categoria)")
    Page<Producto> findByFilters(@Param("nombre") String nombre,
                                 @Param("categoria") Long categoria,
                                 Pageable pageable);

    @Query("SELECT p FROM Producto p WHERE " +
            "(:nombre IS NULL OR p.nombreProducto LIKE %:nombre%) AND " +
            "(:categoria IS NULL OR p.categoria.id = :categoria)")
    List<Producto> findByFiltersCustom(@Param("nombre") String nombre,
                                       @Param("categoria") Long categoria);
}
