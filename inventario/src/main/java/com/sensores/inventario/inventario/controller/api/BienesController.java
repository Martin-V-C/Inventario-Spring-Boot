package com.sensores.inventario.inventario.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.sensores.inventario.inventario.model.entitiesDtos.BienesDto;
import com.sensores.inventario.inventario.model.othersDTO.ApiResponse;
import com.sensores.inventario.inventario.model.othersDTO.Bienflat;
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
    public ResponseEntity<List<BienesDto>> getListaBienes() {
        return ResponseEntity.ok(bienService.getListaBienes());
    }

    @GetMapping("/bien/{id}")
    public ResponseEntity<BienesDto> getBien(@PathVariable Integer id) {
        return ResponseEntity.ok(bienService.getBien(id));
    }


    @PostMapping("/bien")
    public ResponseEntity<ApiResponse<?>> saveBien(@RequestBody Bienflat request) {
        bienService.saveBien(request);
        return ResponseEntity.ok(ApiResponse.builder().message("Bien creado exitosamente").build());
    }

    @DeleteMapping("/bien/{id}")
    public ResponseEntity<?> deleteBien(@PathVariable Integer id) {
        bienService.deleteBien(id);
        return ResponseEntity.ok(ApiResponse.builder().message("Bien eliminado exitosamente").build());
    }

    @PutMapping("/bien/{id}")
    public ResponseEntity<ApiResponse<?>> updateBien(@PathVariable Integer id, @RequestBody Bienflat bien) {
        bienService.updateBien(bien);
        return ResponseEntity.ok(ApiResponse.builder().message("Bien actualizado exitosamente").build());
    }
}
