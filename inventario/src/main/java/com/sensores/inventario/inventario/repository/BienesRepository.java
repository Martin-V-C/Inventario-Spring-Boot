package com.sensores.inventario.inventario.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sensores.inventario.inventario.model.entities.Bienes;
import com.sensores.inventario.inventario.model.entities.Depositario;

@Repository
public interface BienesRepository extends JpaRepository<Bienes, Integer> {

    List<Bienes> findByDepositario(Depositario depositario);

}
