package com.sensores.inventario.inventario.model.dto.auth;

import com.sensores.inventario.inventario.model.dto.DepositarioDto;
import com.sensores.inventario.inventario.model.entities.Depositario;

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
    private DepositarioDto dep;

}
