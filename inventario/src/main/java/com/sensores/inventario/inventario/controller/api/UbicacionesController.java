package com.sensores.inventario.inventario.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sensores.inventario.inventario.model.entitiesDtos.UbicacionDto;
import com.sensores.inventario.inventario.service.apiService.UbicacionService;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api")
public class UbicacionesController {

    @Autowired
    UbicacionService service;

    /**
     * Devuelve una lista de ubicaciones en formato de DTO.
     * @return una lista de UbicacionDto
     */
    @GetMapping("/ubicaciones")
    public List<UbicacionDto> getUbicaciones() {
        return service.getUbicaciones();
    }
    
}
