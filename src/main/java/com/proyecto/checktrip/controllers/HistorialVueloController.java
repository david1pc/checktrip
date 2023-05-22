package com.proyecto.checktrip.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/historial")
@RequiredArgsConstructor

public class HistorialVueloController {

    @GetMapping("/vuelo")
    public String index(){
        return "Hola";
    }

}
