package com.sensores.inventario.inventario.Exceptions;

public  class BadRequestException extends RuntimeException {   
    public BadRequestException(String message) {
        super(message);
    }
}
