package com.sensores.inventario.inventario.service.apiService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sensores.inventario.inventario.Exceptions.ResourceNotFoundException;
import com.sensores.inventario.inventario.model.entities.Bienes;
import com.sensores.inventario.inventario.model.entities.Depositario;
import com.sensores.inventario.inventario.model.entities.Ubicacion;
import com.sensores.inventario.inventario.model.entitiesDtos.BienMapper;
import com.sensores.inventario.inventario.model.entitiesDtos.BienesDto;
import com.sensores.inventario.inventario.model.othersDTO.Bienflat;
import com.sensores.inventario.inventario.repository.BienesRepository;
import com.sensores.inventario.inventario.repository.DepositarioRepositary;
import com.sensores.inventario.inventario.repository.UbicacionesRepository;

@Service
public class BienesServiceImp implements BienService {

    @Autowired
    BienesRepository bienesRepository;

    @Autowired
    DepositarioRepositary depositarioRepositary;

    @Autowired
    UbicacionesRepository ubicacionRepository;

    @Override
    public List<BienesDto> getListaBienes() {
        List<Bienes> bienes = bienesRepository.findAll();
        List<BienesDto> bienesDtos = new ArrayList<>();
        for (Bienes bien : bienes) {
            bienesDtos.add(BienMapper.Mapper.BienToDto(bien));
        }
        return bienesDtos;
    }

    @Override
    public BienesDto getBien(Integer id) {
        return BienMapper.Mapper.BienToDto(bienesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("el bien con id " + id + " no ha sido encontrado")));
    }

    // del controlador se va obtener un BienesDto el cual contiene el nombre
    @Override
    public void saveBien(Bienflat request) {
        if (bienesRepository.existsById(request.getId())) {
            throw new ResourceNotFoundException("el bien con id " + request.getId() + " ya ha sido creado");
        }
        Ubicacion lugar = ubicacionRepository.findByLugar(request.getUbicacion())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "La ubicacion " + request.getUbicacion() + " no ha sido encontrada"));
        Depositario dep = depositarioRepositary.findByNombre(request.getDepositario())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "El depositario " + request.getDepositario() + " no ha sido encontrado"));
        var bien = Bienes.builder()
                .id(request.getId())
                .descripcion(request.getDescripcion())
                .etiqueta(request.getEtiqueta())
                .estado(request.getEstado())
                .depositario(dep)
                .ubicacion(lugar)
                .build();
        bienesRepository.save(bien);
    }

    @Override
    public void deleteBien(Integer id) {
        if (!bienesRepository.existsById(id)) {
            throw new ResourceNotFoundException("El bien con id " + id + " no ha sido encontrado");
        }
        bienesRepository.deleteById(id);
    }

    @Override
    public void updateBien(Bienflat request) {
        Bienes bienes = bienesRepository.findById(request.getId()).orElseThrow(
                () -> new ResourceNotFoundException("el bien con id " + request.getId() + " no ha sido encontrado"));
        Depositario depositario = depositarioRepositary.findByNombre(request.getDepositario())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "El depositario " + request.getDepositario() + " no ha sido encontrado"));
        Ubicacion ubicacion = ubicacionRepository.findByLugar(request.getUbicacion())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "La ubicacion " + request.getUbicacion() + " no ha sido encontrada"));
        bienes.setDescripcion(request.getDescripcion());
        bienes.setEtiqueta(request.getEtiqueta());
        bienes.setEstado(request.getEstado());
        bienes.setDepositario(depositario);
        bienes.setUbicacion(ubicacion);
        bienesRepository.save(bienes);
    }

}
