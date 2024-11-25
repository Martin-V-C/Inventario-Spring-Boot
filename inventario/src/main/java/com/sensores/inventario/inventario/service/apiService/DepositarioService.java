package com.sensores.inventario.inventario.service.apiService;

import java.util.List;

import com.sensores.inventario.inventario.model.entitiesDtos.DepositarioDto;
import com.sensores.inventario.inventario.model.othersDTO.UpdateDepositarioDto;

public interface DepositarioService {

    List<DepositarioDto> getDepositariosDto();

    void deleteDepositario(Integer noEco);

    void updateDepositario(Integer noEco, UpdateDepositarioDto request);

    UpdateDepositarioDto getDepositarioForUpdate(Integer noEco);
}
