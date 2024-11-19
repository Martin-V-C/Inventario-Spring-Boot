package com.sensores.inventario.inventario.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.sensores.inventario.inventario.model.dto.BienesDto;
import com.sensores.inventario.inventario.model.dto.Bienflat;
import com.sensores.inventario.inventario.model.entities.Bienes;
import com.sensores.inventario.inventario.service.apiService.BienService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api")
public class BienesController {

    @Autowired
    BienService bienService;

    @GetMapping("/bienes")
    public List<BienesDto> listaBienes() {
        return bienService.bienestotales();
    }

    @GetMapping("/bien/{id}")
    public BienesDto getMethodName(@PathVariable Integer id) {
        return bienService.buscarBienporID(id);
    }
    


    @PostMapping("/bienes")
    public Bienes GuardarBien(@RequestBody Bienflat bien) {
       System.out.println(bien.toString());
        return bienService.saveBien(bien);
    }

    @DeleteMapping("/bienes/{id}")
    public void deleteBien(@PathVariable Integer id){
        if (bienService.validarExistenciaBien(id)) {
            bienService.deleteBien(id);            
        }
        else{
            System.out.println("El bien no existe");
        }
    }

    @PutMapping("/api/bienes/{id}")
        public Bienes updateBien(@PathVariable Integer id, @RequestBody Bienflat bien) {
    if (!bienService.validarExistenciaBien(id)) {
        System.out.println("El bien no existe");
        return null;
    }
    return bienService.saveBien(bien);
}
    
}
                            