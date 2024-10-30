package com.sensores.inventario.inventario.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sensores.inventario.inventario.model.dto.UbicacionDto;
import com.sensores.inventario.inventario.service.apiService.UbicacionService;

@RestController
@RequestMapping("/api")
public class UbicacionesController {

    @Autowired
    UbicacionService service;

    @GetMapping("/ubicaciones")
    @CrossOrigin(origins = "http://127.0.0.1:5500")
    public List<UbicacionDto> getUbicaciones() {
        return service.getUbicaciones();
    }
    
}
