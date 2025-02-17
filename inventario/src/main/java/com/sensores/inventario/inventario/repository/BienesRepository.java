package com.sensores.inventario.inventario.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sensores.inventario.inventario.model.entities.Bienes;
import com.sensores.inventario.inventario.model.entities.Depositario;


@Repository
public interface BienesRepository extends JpaRepository<Bienes, Integer> {

    List<Bienes> findByDepositario(Depositario depositario);

    @Query("SELECT b.etiqueta, COUNT(b) FROM Bienes b GROUP BY b.etiqueta")
    List<Object[]> contarBienesPorEtiqueta();

    @Query("SELECT b.ubicacion.lugar, COUNT(b) FROM Bienes b GROUP BY b.ubicacion")
    List<Object[]> contarBienesPorUbicacion();

    @Query("SELECT b.estado, COUNT(b) FROM Bienes b GROUP BY b.estado")
    List<Object[]> contarBienesPorEstado();

    @Query("SELECT b.depositario.nombre, COUNT(b) FROM Bienes b GROUP BY b.depositario")
    List<Object[]> contarBienesPorDepositario();
}
