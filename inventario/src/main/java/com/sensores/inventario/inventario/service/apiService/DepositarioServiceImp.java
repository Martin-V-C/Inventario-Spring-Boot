package com.sensores.inventario.inventario.service.apiService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sensores.inventario.inventario.model.dto.DepositarioDto;
import com.sensores.inventario.inventario.model.dto.DepositarioMapper;
import com.sensores.inventario.inventario.model.entities.Depositario;
import com.sensores.inventario.inventario.model.repository.DepositarioRepositary;

@Service
public class DepositarioServiceImp implements DepositarioService{

    @Autowired
    DepositarioRepositary depRepositary;

    @Override
    public List<DepositarioDto> getDepositariosDto() {
        List<Depositario> depositarios = depRepositary.findAll();
        List<DepositarioDto> depositarioDtos = new ArrayList<>();
        for (Depositario depositario : depositarios) {
            depositarioDtos.add(DepositarioMapper.Mapper.deptoDto(depositario));
        }
        return depositarioDtos;
        
    }

}
