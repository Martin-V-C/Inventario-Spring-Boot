package com.sensores.inventario.inventario.service.apiService;

import java.util.List;

import com.sensores.inventario.inventario.model.dto.DepositarioDto;

public interface DepositarioService {

   /* List<DepositarioBienesDto> getDepositariosBienes();*/
    List<DepositarioDto> getDepositariosDto();
}