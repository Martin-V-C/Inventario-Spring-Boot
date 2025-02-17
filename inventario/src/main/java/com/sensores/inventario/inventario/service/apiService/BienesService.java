package com.sensores.inventario.inventario.service.apiService;

import java.util.List;

import com.sensores.inventario.inventario.model.entitiesDtos.BienesDto;
import com.sensores.inventario.inventario.model.othersDTO.Bienflat;



public interface BienesService {

    List<BienesDto> getListaBienes();

    void saveBien(Bienflat bien);

    void deleteBien(Integer id);

    Bienflat getBien(Integer id);

    void updateBien(Bienflat bien);

    public List<Object[]> contarBienesPorEtiqueta();
    
    public List<Object[]> contarBienesPorUbicacion();

    public List<Object[]> contarBienesPorEstado();

    public List<Object[]> contarBienesPorDepositario();

}
