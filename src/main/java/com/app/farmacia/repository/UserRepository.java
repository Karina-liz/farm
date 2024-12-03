package com.app.farmacia.repository;

import com.app.farmacia.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    @Query("SELECT u FROM User u WHERE " +
            "(:nombre IS NULL OR u.nombre LIKE %:nombre%) AND " +
            "(:apellido IS NULL OR u.apellido LIKE %:apellido%) AND " +
            "(:username IS NULL OR u.username LIKE %:username%)")
    Page<User> findByFilters(@Param("nombre") String nombre,
                             @Param("apellido") String apellido,
                             @Param("username") String username,
                             Pageable pageable);
}
