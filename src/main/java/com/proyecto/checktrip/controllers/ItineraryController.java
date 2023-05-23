package com.proyecto.checktrip.controllers;

import com.proyecto.checktrip.dto.ClienteViajeIdaRequestDTO;
import com.proyecto.checktrip.dto.ClienteViajeIdaVueltaRequestDTO;
import com.proyecto.checktrip.dto.ItinerariesClientDTO;
import com.proyecto.checktrip.services.ClienteViajeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/itinerary")
@CrossOrigin(origins = {"http://localhost:4200", "https://checktrip-software.web.app/"},
        methods = {RequestMethod.POST,
                RequestMethod.GET
        })
public class ItineraryController {
        @Autowired
        private ClienteViajeServiceImpl clienteViajeService;

        @PreAuthorize("hasAuthority('SCOPE_CLIENT')")
        @PostMapping("/ida")
        public String createItineraryIda(@RequestBody ClienteViajeIdaRequestDTO itinerary) {
                return this.clienteViajeService.guardarItinerarioIda(itinerary);
        }

        @PreAuthorize("hasAuthority('SCOPE_CLIENT')")
        @PostMapping("/ida-vuelta")
        public String createItineraryIdaVuelta(@RequestBody ClienteViajeIdaVueltaRequestDTO itinerary) {
                return this.clienteViajeService.guardarItinerarioIdaVuelta(itinerary);
        }

        @PreAuthorize("hasAuthority('SCOPE_CLIENT')")
        @PostMapping("/")
        public ItinerariesClientDTO findAllItineraries(@RequestBody String username){
                return this.clienteViajeService.obtenerViajes(username);
        }
}
