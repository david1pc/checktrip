package com.proyecto.checktrip.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@Component
public class AccessDeniedHandlerSeguridad implements org.springframework.security.web.access.AccessDeniedHandler{
    private static final Logger logger = LoggerFactory.getLogger(AccessDeniedHandlerSeguridad.class);

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException {
        logger.error("Access denied: {}", accessDeniedException.getMessage());
        response.setContentType("application/json");
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("mensaje", "No tiene los permisos necesarios para acceder a este recurso");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().write(convertObjectToJson(respuesta));
    }

    public String convertObjectToJson(Object object) throws JsonProcessingException {
        if (object == null) {
            return null;
        }
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(object);
    }
}
