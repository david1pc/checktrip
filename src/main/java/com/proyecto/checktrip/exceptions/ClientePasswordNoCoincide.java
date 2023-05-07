package com.proyecto.checktrip.exceptions;

public class ClientePasswordNoCoincide extends RuntimeException {
    public ClientePasswordNoCoincide(String msj){
        super(msj);
    }
}
