package com.proyecto.checktrip.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler({PersonaYaExiste.class,
            PersonaNoExiste.class,
            ClienteYaExiste.class,
            PersonaInactiva.class,
    })
    @ResponseBody
    public ResponseEntity<Map<String, Object>> handlePersonaYaExisteException(PersonaYaExiste exception) {
        Map<String, Object> errorMap = new HashMap<>();
        errorMap.put("status", BAD_REQUEST.value());
        errorMap.put("detail", exception.getMessage());
        errorMap.put("title", BAD_REQUEST.name());
        return new ResponseEntity<>(errorMap, BAD_REQUEST);
    }
}
