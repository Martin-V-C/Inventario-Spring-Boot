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
// @RequiredArgsConstructor
public class AuthController {

    @Autowired
    AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @GetMapping("/prueba")
    public String getMethodName() {
        return "End point publico de prueba";
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }

}
