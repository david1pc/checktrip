package com.proyecto.checktrip.controllers;

import com.proyecto.checktrip.dto.ClientRequestDTO;
import com.proyecto.checktrip.dto.ClientResponseDTO;
import com.proyecto.checktrip.dto.ClienteViajeIdaRequestDTO;
import com.proyecto.checktrip.dto.ClienteViajeIdaResponseDTO;
import com.proyecto.checktrip.services.ClienteViajeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/itinerary")
@CrossOrigin(origins = {"http://localhost:4200", "https://checktrip-software.web.app/", "http://localhost:8080"},
        methods = {RequestMethod.POST,
                RequestMethod.GET
        })
public class ItineraryController {
        @Autowired
        private ClienteViajeService clienteViajeService;

        @PostMapping("/")
        public ClienteViajeIdaResponseDTO createItinerary(@RequestBody ClienteViajeIdaRequestDTO itinerary) {
                return this.clienteViajeService.guardarItinerarioIda(itinerary);
        }

        @GetMapping("/obtener")
        public String index() {
                return "Hola";
        }
}
