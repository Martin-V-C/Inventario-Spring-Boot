package com.sensores.inventario.inventario.service.apiService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sensores.inventario.inventario.model.entities.Ubicacion;
import com.sensores.inventario.inventario.model.entitiesDtos.UbicacionDto;
import com.sensores.inventario.inventario.model.entitiesDtos.UbicacionMapper;
import com.sensores.inventario.inventario.repository.UbicacionesRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class UbicacionServiceImp implements UbicacionService{

    @Autowired
    UbicacionesRepository repository;

    /**
     * Obtiene una lista de todas las ubicaciones en la base de datos.
     * @return una lista de UbicacionDto que representa las ubicaciones
     */
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
