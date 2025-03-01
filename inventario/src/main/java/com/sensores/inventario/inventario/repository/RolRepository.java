package com.sensores.inventario.inventario.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sensores.inventario.inventario.model.entities.Rol;

@Repository
public interface RolRepository extends JpaRepository<Rol, Integer>{

    Optional<Rol> findByTipo(String tipo);
}
