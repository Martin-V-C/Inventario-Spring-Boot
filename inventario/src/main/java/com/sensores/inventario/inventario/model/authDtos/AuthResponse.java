package com.sensores.inventario.inventario.model.authDtos;

import com.sensores.inventario.inventario.model.entitiesDtos.DepositarioDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthResponse {
    private String token;
    private DepositarioDto depositario;

}
