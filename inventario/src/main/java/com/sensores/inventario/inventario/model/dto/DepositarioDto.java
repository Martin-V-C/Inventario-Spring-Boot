package com.sensores.inventario.inventario.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepositarioDto {
    private Integer no_economico;
    private String nombre;
    private Roldto rol;

}
