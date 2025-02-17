package com.sensores.inventario.inventario.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sensores.inventario.inventario.model.authDtos.RegisterRequest;
import com.sensores.inventario.inventario.model.entitiesDtos.DepositarioDto;
import com.sensores.inventario.inventario.model.othersDTO.ApiResponse;
import com.sensores.inventario.inventario.model.othersDTO.UpdateDepositarioDto;
import com.sensores.inventario.inventario.service.apiService.DepositarioService;
import com.sensores.inventario.inventario.service.securityService.AuthService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;




@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api")
public class DepositarioController {

    @Autowired
    DepositarioService depositarioService;
    @Autowired
    AuthService authService;
    

    /**
     * Obtiene una lista de todos los depositarios.
     *
     * @return una lista de DepositarioDto
     */
    @GetMapping("/depositarios")
    public ResponseEntity<List<DepositarioDto>> listaDepositarios() {
        return ResponseEntity.ok(depositarioService.getDepositariosDto());
    }

/**
 * Obtiene un depositario para su actualización.
 *
 * @param noEco el número económico del depositario a obtener
 * @return el depositario con el número económico especificado
 */

    @GetMapping("depositarios/{noEco}")
    public ResponseEntity<UpdateDepositarioDto> getDepositario (@PathVariable Integer noEco) {
        UpdateDepositarioDto response=depositarioService.getDepositarioForUpdate(noEco);
        return ResponseEntity.ok(response);
    }
    
/**
 * Elimina el depositario con el número económico especificado.
 *
 * @param noEco el número económico del depositario a eliminar
 * @return objeto que contiene el mensaje de éxito
 */

    @DeleteMapping("/depositarios/{noEco}")
    public ResponseEntity<ApiResponse<?>> deleteDepositario(@PathVariable Integer noEco) { 
        depositarioService.deleteDepositario(noEco);
        return ResponseEntity.ok(ApiResponse.builder().message("Depositario Eliminado Exitosamente").build());
    }

    /**
     * Actualiza el depositario con el número económico especificado.
     *
     * @param noEco el número económico del depositario a actualizar
     * @param request objeto que contiene la nueva información del depositario
     * @return objeto que contiene el mensaje de éxito
     */
    @PutMapping("/depositarios/{noEco}")
    public ResponseEntity<ApiResponse<?>> updateDepositario(@PathVariable Integer noEco, @RequestBody UpdateDepositarioDto request) {
        depositarioService.updateDepositario(noEco, request);
        return ResponseEntity.ok(ApiResponse.builder().message( "Depositario Actualizado Exitosamente").build());
    }
    /**
     * Registra un depositario.
     * 
     * @param request objeto que contiene la informacion del depositario a registrar
     * @return objeto que contiene el mensaje de exito
     */
    @PostMapping("/register")
    public ResponseEntity<ApiResponse<?>> register(@RequestBody RegisterRequest request) {
        authService.register(request);
        return ResponseEntity.ok(ApiResponse.builder().message("Depositario creado exitosamente").build());
    }

}
