package com.sensores.inventario.inventario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sensores.inventario.inventario.model.entities.Depositario;
import java.util.Optional;
@Repository
public interface DepositarioRepositary extends JpaRepository<Depositario,Integer> {

    Optional<Depositario> findByNombre(String nombre);
    Optional<Depositario> findByUsername(String username);
    Optional<Depositario> findByNumeroEco(Integer numeroEco);
    void deleteByNumeroEco(Integer numeroEco);
    Boolean existsByNumeroEco(Integer numeroEco);
    
}
