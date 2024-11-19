package com.sensores.inventario.inventario.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sensores.inventario.inventario.model.dto.DepositarioDto;
import com.sensores.inventario.inventario.model.dto.auth.AuthResponse;
import com.sensores.inventario.inventario.model.dto.auth.RegisterRequest;
import com.sensores.inventario.inventario.service.apiService.DepositarioService;
import com.sensores.inventario.inventario.service.securityService.AuthService;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api")
public class DepositarioController {

    @Autowired
    DepositarioService depositarioService;
    @Autowired
    AuthService authService;

    @GetMapping("depositarios")
    public List<DepositarioDto> listaDepositarios() {
        return depositarioService.getDepositariosDto();
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }

}
