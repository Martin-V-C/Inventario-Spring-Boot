package com.sensores.inventario.inventario.service.securityService;

import com.sensores.inventario.inventario.model.authDtos.AuthResponse;
import com.sensores.inventario.inventario.model.authDtos.LoginRequest;
import com.sensores.inventario.inventario.model.authDtos.RegisterRequest;

public interface AuthService {

    AuthResponse register (RegisterRequest request);
    AuthResponse login (LoginRequest request);

}
