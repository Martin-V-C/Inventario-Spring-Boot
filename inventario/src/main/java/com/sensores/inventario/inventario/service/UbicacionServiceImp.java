package com.sensores.inventario.inventario.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sensores.inventario.inventario.model.dto.UbicacionDto;
import com.sensores.inventario.inventario.model.dto.UbicacionMapper;
import com.sensores.inventario.inventario.model.entities.Ubicacion;
import com.sensores.inventario.inventario.model.repository.UbicaacionesRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class UbicacionServiceImp implements UbicacionService{

    @Autowired
    UbicaacionesRepository repository;

    @Override
    public List<UbicacionDto> getUbicaciones() {
        List<Ubicacion> ubicaciones = repository.findAll();
        List<UbicacionDto> ubicacionDtos=new ArrayList<>();
        for (Ubicacion ubicacion : ubicaciones) {
            ubicacionDtos.add(UbicacionMapper.Mapper.ubicacionToDto(ubicacion));
        }
        return ubicacionDtos;

    }
    

}
