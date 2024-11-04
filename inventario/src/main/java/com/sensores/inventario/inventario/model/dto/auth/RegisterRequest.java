package com.sensores.inventario.inventario.model.dto.auth;

import com.sensores.inventario.inventario.model.entities.Rol;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterRequest {
    private Integer no_economico;
    private String nombre;
    private String username;
    private String password;
    private Rol rol;
}
