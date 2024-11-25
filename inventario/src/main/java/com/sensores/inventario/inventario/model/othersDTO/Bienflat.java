package com.sensores.inventario.inventario.model.othersDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Bienflat {
    private Integer id;
    private String descripcion;
    private String estado;
    private String etiqueta;
    private String depositario;
    private String ubicacion;
}
