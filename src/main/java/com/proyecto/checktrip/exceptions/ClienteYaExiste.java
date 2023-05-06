package com.proyecto.checktrip.exceptions;

public class ClienteYaExiste extends RuntimeException {
    public ClienteYaExiste(String msj){
        super(msj);
    }
}
