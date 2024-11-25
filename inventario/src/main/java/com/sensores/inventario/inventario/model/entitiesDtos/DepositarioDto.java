package com.sensores.inventario.inventario.model.entitiesDtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepositarioDto {
    private Integer numeroEco;
    private String nombre;
    private Roldto rol;

}
