package com.sensores.inventario.inventario.model.othersDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateDepositarioDto {

    private String nombre;
    private String username;
    private String password;
    private String rol;

}
