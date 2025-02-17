package com.sensores.inventario.inventario.controller.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
//import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sensores.inventario.inventario.model.authDtos.AuthResponse;
import com.sensores.inventario.inventario.model.authDtos.LoginRequest;
import com.sensores.inventario.inventario.model.authDtos.RegisterRequest;
import com.sensores.inventario.inventario.service.securityService.AuthService;

@RestController
@CrossOrigin
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthService authService;

    /**
     * Realiza la autenticacion de un depositario.
     *
     * @param request objeto que contiene la informacion del depositario a autenticar
     * @return objeto que contiene el token de autenticacion y la informacion del depositario
     */
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

    /**
     * Registra un depositario.
     *
     * @param request objeto que contiene la informacion del depositario a registrar
     * @return objeto que contiene el token de autenticacion y la informacion del depositario
     */
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }

}
