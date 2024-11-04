package com.sensores.inventario.inventario.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sensores.inventario.inventario.model.entities.Rol;


public interface RolRepository extends JpaRepository<Rol, Integer>{

    Optional<Rol> findByTipo(String tipo);
}
