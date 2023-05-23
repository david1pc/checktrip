package com.proyecto.checktrip.exceptions;

public class JavaMailError extends RuntimeException {
    public JavaMailError(String msj){
        super(msj);
    }
}
