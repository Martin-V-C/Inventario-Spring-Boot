package com.sensores.inventario.inventario.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Bienflat {
    private Integer id;
    private String descripcion;
    private String estado;
    private String etiqueta;
    private String depositario;
    private String ubicacion;
}
