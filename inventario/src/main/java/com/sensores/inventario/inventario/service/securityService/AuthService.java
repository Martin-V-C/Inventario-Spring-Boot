package com.sensores.inventario.inventario.service.securityService;

import com.sensores.inventario.inventario.model.dto.auth.AuthResponse;
import com.sensores.inventario.inventario.model.dto.auth.LoginRequest;
import com.sensores.inventario.inventario.model.dto.auth.RegisterRequest;

public interface AuthService {

    AuthResponse register (RegisterRequest request);
    AuthResponse login (LoginRequest request);

}
