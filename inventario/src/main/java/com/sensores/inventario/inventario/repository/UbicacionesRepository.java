package com.sensores.inventario.inventario.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sensores.inventario.inventario.model.entities.Ubicacion;

@Repository
public interface UbicacionesRepository extends JpaRepository<Ubicacion, Integer> {

    Optional<Ubicacion> findByLugar(String lugar);
}
