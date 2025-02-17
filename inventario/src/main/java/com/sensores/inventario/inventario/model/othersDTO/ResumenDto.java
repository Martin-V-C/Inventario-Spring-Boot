package com.sensores.inventario.inventario.model.othersDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class ResumenDto {
    private String etiqueta;
    private Long cantidad;
}
