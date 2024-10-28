package com.sensores.inventario.inventario.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.sensores.inventario.inventario.model.dto.BienesDto;
import com.sensores.inventario.inventario.model.dto.Bienflat;
import com.sensores.inventario.inventario.model.entities.Bienes;
import com.sensores.inventario.inventario.service.BienService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;




@RestController
@RequestMapping("/api")
public class BienesController {

    @Autowired
    BienService bienService;

    @GetMapping("/bienes")
    @CrossOrigin(origins = "http://127.0.0.1:5500")
    public List<BienesDto> listaBienes() {
        return bienService.bienestotales();
    }


    @PostMapping("/bienes")
    @CrossOrigin(origins = "http://127.0.0.1:5500")
    public Bienes GuardarBien(@RequestBody Bienflat bien) {
       System.out.println(bien.toString());

        return bienService.saveBien(bien);
    }

    @DeleteMapping("/bienes/{id}")
    @CrossOrigin(origins = "http://127.0.0.1:5500")
    public void deleteBien(@PathVariable Integer id){
        if (bienService.validarExistenciaBien(id)) {
            bienService.deleteBien(id);            
        }
        else{
            System.out.println("El bien no existe");
        }
    }

    @PutMapping("/api/bienes/{id}")
    @CrossOrigin(origins = "http://127.0.0.1:5500")
        public Bienes updateBien(@PathVariable Integer id, @RequestBody Bienflat bien) {
    if (!bienService.validarExistenciaBien(id)) {
        //return ResponseEntity.notFound().build(); // 404 Not Found
        System.out.println("El bien no existe");
        return null;
    }
    return bienService.saveBien(bien);
}
    
}
                            