package com.sensores.inventario.inventario.service.apiService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sensores.inventario.inventario.model.dto.BienMapper;
import com.sensores.inventario.inventario.model.dto.BienesDto;
import com.sensores.inventario.inventario.model.dto.Bienflat;
import com.sensores.inventario.inventario.model.entities.Bienes;
import com.sensores.inventario.inventario.model.entities.Depositario;
import com.sensores.inventario.inventario.model.entities.Ubicacion;
import com.sensores.inventario.inventario.model.repository.BienesRepository;
import com.sensores.inventario.inventario.model.repository.DepositarioRepositary;
import com.sensores.inventario.inventario.model.repository.UbicacionesRepository;

@Service

public class BienesServiceImp implements BienService {
   
    @Autowired
    BienesRepository bienesRepository;

    @Autowired
    DepositarioRepositary depositarioRepositary;

    @Autowired
    UbicacionesRepository ubicaacionRepository;

    @Override
    public List<BienesDto> bienestotales() {
        List<Bienes> bienes= bienesRepository.findAll();
        List<BienesDto> bienesDtos = new ArrayList<>();
        for (Bienes bien : bienes) {
            bienesDtos.add(BienMapper.Mapper.BienToDto(bien));
         }
        return bienesDtos;
    }

    //del controlador se va obtener un BienesDto el cual contiene el nombre 
    @Override
    public Bienes saveBien(Bienflat bienflat) {
        
        Ubicacion lugar = ubicaacionRepository.findByLugar(bienflat.getUbicacion()).get();
        Depositario dep = depositarioRepositary.findByNombre(bienflat.getDepositario()).get();

        Bienes bien = new Bienes();

        bien.setId(bienflat.getId());
        bien.setDescripcion(bienflat.getDescripcion());
        bien.setEstado(bienflat.getEstado());
        bien.setEtiqueta(bienflat.getEtiqueta());
        bien.setDepositario(dep);
        bien.setUbicacion(lugar);
        
        return bienesRepository.save(bien);
    }

    @Override
    public void deleteBien(Integer id) {
        bienesRepository.deleteById(id);
    }

    @Override
    public boolean validarExistenciaBien(Integer id) {
        return bienesRepository.existsById(id);
    }

    @Override
    //Buesca un bien por su ID, regresa un optional o null 
    //TODO (implementar exepcion)
    public BienesDto buscarBienporID(Integer id) {
       Bienes bien = bienesRepository.findById(id).orElse(null);
        if (bien!=null) {
            BienesDto bienDTO = BienMapper.Mapper.BienToDto(bien);
            return bienDTO;
        } else {
            return null;
        }
    }

}
