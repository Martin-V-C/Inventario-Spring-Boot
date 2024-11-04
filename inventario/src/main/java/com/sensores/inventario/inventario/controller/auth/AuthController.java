package com.sensores.inventario.inventario.controller.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
//import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sensores.inventario.inventario.model.dto.auth.AuthResponse;
import com.sensores.inventario.inventario.model.dto.auth.LoginRequest;
import com.sensores.inventario.inventario.model.dto.auth.RegisterRequest;
import com.sensores.inventario.inventario.service.securityService.AuthService;

import org.springframework.web.bind.annotation.GetMapping;



@RestController
@CrossOrigin
@RequestMapping("/auth")
//@RequiredArgsConstructor
public class AuthController {

    @Autowired
    AuthService authService;    
    
@PostMapping("/login")
public  ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request){
    return ResponseEntity.ok(authService.login(request));
}

@PostMapping("/register")
public  ResponseEntity<AuthResponse> login(@RequestBody RegisterRequest request){
    return ResponseEntity.ok(authService.register(request));
}

@GetMapping("/prueba")
public String getMethodName() {
    return "End point publico de prueba";
}



}
