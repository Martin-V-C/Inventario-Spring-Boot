package com.sensores.inventario.inventario.service.apiService;

import java.util.List;

import com.sensores.inventario.inventario.model.entitiesDtos.BienesDto;
import com.sensores.inventario.inventario.model.othersDTO.Bienflat;


public interface BienService {

    List<BienesDto> getListaBienes();

    void saveBien(Bienflat bien);

    void deleteBien(Integer id);

    BienesDto getBien(Integer id);

    void updateBien(Bienflat bien);

}
