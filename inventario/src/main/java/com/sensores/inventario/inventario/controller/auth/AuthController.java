package com.sensores.inventario.inventario.controller.auth;

//import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {
    
@PostMapping("/login")
public String login(){
    return "Direccion publica para el login";
}



}
