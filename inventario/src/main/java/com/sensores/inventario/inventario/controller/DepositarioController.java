package com.sensores.inventario.inventario.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.sensores.inventario.inventario.model.dto.DepositarioDto;
import com.sensores.inventario.inventario.service.DepositarioService;


@RestController
@RequestMapping("/api")
public class DepositarioController {

    @Autowired
    DepositarioService depositarioService;

    @GetMapping("depositarios")
    @CrossOrigin(origins = "http://127.0.0.1:5500")
    public List<DepositarioDto> listaDepositarios() {
        return depositarioService.getDepositariosDto();
    }
    
    
}
