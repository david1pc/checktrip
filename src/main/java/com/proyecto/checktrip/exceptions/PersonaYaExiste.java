package com.proyecto.checktrip.exceptions;

public class PersonaYaExiste extends RuntimeException {
    public PersonaYaExiste(String msj){
        super(msj);
    }
}
