package com.proyecto.checktrip.exceptions;

public class PersonaInactiva extends RuntimeException {
    public PersonaInactiva(String msj){
        super(msj);
    }
}
