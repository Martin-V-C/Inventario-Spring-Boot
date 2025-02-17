package com.sensores.inventario.inventario.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.sensores.inventario.inventario.model.entitiesDtos.BienesDto;
import com.sensores.inventario.inventario.model.othersDTO.ApiResponse;
import com.sensores.inventario.inventario.model.othersDTO.Bienflat;
import com.sensores.inventario.inventario.service.apiService.BienesService;

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
    BienesService bienService;


    /**
     * Devuelve una lista de todos los bienes
     * @return una lista de BienesDto
     */
    @GetMapping("/bienes")
    public ResponseEntity<List<BienesDto>> getListaBienes() {
        return ResponseEntity.ok(bienService.getListaBienes());
    }
    /**
     * Devuelve una lista de resumenes, donde cada resumen contiene el nombre de una ubicacion y el conteo de bienes que tiene en esa ubicacion.
     * @return una lista de Object[] con los resumenes
     */
    @GetMapping("/bienes/ubicaciones")
    public ResponseEntity<List<Object[]>> getResumenPorUbicacion() {
        return ResponseEntity.ok(bienService.contarBienesPorUbicacion());
    }
    /**
     * Devuelve una lista de resumenes, donde cada resumen contiene el nombre de una etiqueta y el conteo de bienes que tiene en esa etiqueta.
     * @return una lista de Object[] con los resumenes
     */
    @GetMapping("/bienes/etiquetas")
    public ResponseEntity<List<Object[]>> getResumenPorEtiqueta() {
        return ResponseEntity.ok(bienService.contarBienesPorEtiqueta());
    }

    /**
     * Devuelve una lista de resumenes, donde cada resumen contiene el nombre de un estado y el conteo de bienes que tiene en ese estado.
     * @return una lista de Object[] con los resumenes
     */
    @GetMapping("/bienes/estados")
    public ResponseEntity<List<Object[]>> getResumenPorEstado() {
        return ResponseEntity.ok(bienService.contarBienesPorEstado());
    }

/**
 * Devuelve una lista de resumenes, donde cada resumen contiene el nombre de un depositario 
 * y el conteo de bienes que tiene ese depositario.
 * @return una lista de Object[] con los resumenes
 */
    @GetMapping("/bienes/depositarios")
    public ResponseEntity<List<Object[]>> getResumenPorDepositario() {
        return ResponseEntity.ok(bienService.contarBienesPorDepositario());
    }
    
    /**
     * Devuelve el bien con el id especificado
     * @param id el id del bien a obtener
     * @return el bien con el id especificado
     */
    @GetMapping("/bien/{id}")
    public ResponseEntity<Bienflat> getBien(@PathVariable Integer id) {
        return ResponseEntity.ok(bienService.getBien(id));
    }


    /**
     * Crea un nuevo bien.
     * @param request objeto que contiene la informacion del bien a crear
     * @return objeto que contiene el mensaje de exito
     */
    @PostMapping("/bien")
    public ResponseEntity<ApiResponse<?>> saveBien(@RequestBody Bienflat request) {
        bienService.saveBien(request);
        return ResponseEntity.ok(ApiResponse.builder().message("Bien creado exitosamente").build());
    }

    /**
     * Elimina el bien con el id especificado
     * @param id el id del bien a eliminar
     * @return objeto que contiene el mensaje de exito
     */
    @DeleteMapping("/bien/{id}")
    public ResponseEntity<?> deleteBien(@PathVariable Integer id) {
        bienService.deleteBien(id);
        return ResponseEntity.ok(ApiResponse.builder().message("Bien eliminado exitosamente").build());
    }

/**
 * Actualiza el bien con el id especificado.
 * 
 * @param id el id del bien a actualizar
 * @param bien objeto que contiene la nueva informacion del bien
 * @return objeto que contiene el mensaje de exito
 */
    @PutMapping("/bien/{id}")
    public ResponseEntity<ApiResponse<?>> updateBien(@PathVariable Integer id, @RequestBody Bienflat bien) {
        bienService.updateBien(bien);
        return ResponseEntity.ok(ApiResponse.builder().message("Bien actualizado exitosamente").build());
    }


}
