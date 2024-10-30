package com.sensores.inventario.inventario.service.apiService;

import java.util.List;

import com.sensores.inventario.inventario.model.dto.BienesDto;
import com.sensores.inventario.inventario.model.dto.Bienflat;
import com.sensores.inventario.inventario.model.entities.Bienes;


public interface BienService {

    List<BienesDto> bienestotales();

    Bienes saveBien(Bienflat bien);

    void deleteBien(Integer id);

    boolean validarExistenciaBien(Integer id);

    BienesDto buscarBienporID(Integer id);

}
