package com.sensores.inventario.inventario.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.sensores.inventario.inventario.Exceptions.BadRequestException;
import com.sensores.inventario.inventario.Exceptions.ResourceNotFoundException;
import com.sensores.inventario.inventario.model.othersDTO.ApiResponse;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleBadRequestException(ResourceNotFoundException ex) {
        ApiResponse<?> response = ApiResponse.builder()
        .message("Recurso no encontrado")
        .data(ex.getMessage())
        .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<?> handleBadRequestException(BadRequestException ex) {
        ApiResponse<?> response = ApiResponse.builder()
        .message("Error en la peticion")
        .data(ex.getMessage())
        .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleBadRequestException(Exception ex) {
        ApiResponse<?> response = ApiResponse.builder()
        .message("Ups, algo salio mal")
        .data(ex.getMessage())
        .build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

}
