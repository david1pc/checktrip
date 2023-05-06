package com.proyecto.checktrip.exceptions;

public class PersonaNoExiste extends RuntimeException{
    public PersonaNoExiste(String msj){
        super(msj);
    }
}
