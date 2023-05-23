package com.proyecto.checktrip.controllers;

import com.proyecto.checktrip.dto.ClienteViajeIdaRequestDTO;
import com.proyecto.checktrip.dto.ClienteViajeIdaVueltaRequestDTO;
import com.proyecto.checktrip.dto.ItinerariesClientDTO;
import com.proyecto.checktrip.services.ClienteViajeServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/itinerary")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:4200", "https://checktrip-software.web.app/"},
        methods = {RequestMethod.POST, RequestMethod.GET})
public class ItineraryController {
        private final ClienteViajeServiceImpl clienteViajeService;

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

        @PreAuthorize("hasAuthority('SCOPE_CLIENT')")
        @GetMapping("/index")
        public String index(Principal principal){
                return "Hola " + principal.getName();
        }
}
