package com.sensores.inventario.inventario.model.entitiesDtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BienesDto {

    private Integer id;
    private String descripcion;
    private String estado;
    private String etiqueta;
    private DepositarioDto depositario;
    private UbicacionDto ubicacion;

}
