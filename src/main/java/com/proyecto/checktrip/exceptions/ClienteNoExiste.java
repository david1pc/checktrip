package com.proyecto.checktrip.exceptions;

public class ClienteNoExiste extends RuntimeException {
    public ClienteNoExiste(String msj){
        super(msj);
    }
}
