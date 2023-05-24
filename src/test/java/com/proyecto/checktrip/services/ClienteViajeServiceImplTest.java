package com.proyecto.checktrip.services;

import com.proyecto.checktrip.controllers.AuthController;
import com.proyecto.checktrip.dto.*;
import com.proyecto.checktrip.entities.ClienteIdaViajes;
import com.proyecto.checktrip.entities.Role;
import com.proyecto.checktrip.entities.Viaje;
import com.proyecto.checktrip.repo.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.token.TokenService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
class ClienteViajeServiceImplTest {
    @Autowired
    private ClienteViajeService clienteViajeService;
    @MockBean
    private JwtEncoder jwtEncoder;
    @MockBean
    private JwtDecoder jwtDecoder;
    @MockBean
    private TokenService tokenService;

    @Test
    void deberiaCrearItinerarioIda() {
        String expected = "Se ha creado su itinerario de ida correctamente";
        ViajeDTO viajeDTO = this.retornarViaje();
        ClienteViajeIdaRequestDTO clienteViajeIdaRequestDTO = ClienteViajeIdaRequestDTO.builder()
                .username("davidpc")
                .viaje(viajeDTO)
                .fechaCreacion(LocalDateTime.now())
                .build();
        ClienteViajeIdaResponseDTO respuesta = this.clienteViajeService.guardarItinerarioIda(clienteViajeIdaRequestDTO);
        Assertions.assertThat(expected).isEqualTo(respuesta.descripcion());
    }

    @Test
    void deberiaCrearItinerarioIdaVuelta() {
        String expected = "Se ha creado su itinerario de ida y vuelta correctamente";
        ViajeDTO viajeIdaDTO = this.retornarViaje();
        ViajeDTO viajeVueltaDTO = this.retornarViaje();
        ClienteViajeIdaVueltaRequestDTO clienteViajeIdaVueltaRequestDTO = ClienteViajeIdaVueltaRequestDTO.builder()
                .username("davidpc")
                .viajeIda(viajeIdaDTO)
                .viajeVuelta(viajeVueltaDTO)
                .fechaCreacion(LocalDateTime.now())
                .build();
        ClienteViajeIdaVueltaResponseDTO respuesta = this.clienteViajeService.guardarItinerarioIdaVuelta(clienteViajeIdaVueltaRequestDTO);
        Assertions.assertThat(expected).isEqualTo(respuesta.descripcion());
    }

    @Test
    void deberiaBuscarViajes() {
        String username = "davidpc";
        ItinerariesClientDTO itineraries = this.clienteViajeService.obtenerViajes(username);
        Assertions.assertThat(itineraries).isNotNull();
    }

    private ViajeDTO retornarViaje(){
        CarriersDTO carriersDTO = CarriersDTO.builder()
                .id("AC")
                .name("ACM")
                .build();

        AircraftDTO aircraftDTO = AircraftDTO.builder()
                .id("ER")
                .name("ERT")
                .build();

        List<CarriersDTO> carriersDTOList = new ArrayList<>();
        List<AircraftDTO> aircraftDTOSList = new ArrayList<>();

        carriersDTOList.add(carriersDTO);
        aircraftDTOSList.add(aircraftDTO);

        DictionariesDTO dictionaries = DictionariesDTO.builder()
                .carriers(carriersDTOList)
                .aircraft(aircraftDTOSList)
                .build();

        PriceDTO priceDTO = PriceDTO.builder()
                .base(2515.5)
                .total(6262.55)
                .currency("COP")
                .grandTotal(525.55)
                .build();

        ArrivalDTO arrivalDTO = ArrivalDTO.builder()
                .at(LocalDateTime.now())
                .iataCode("AXM")
                .terminal("CO")
                .build();

        ArrivalDTO departureDTO = ArrivalDTO.builder()
                .at(LocalDateTime.now())
                .iataCode("BOG")
                .terminal("CO")
                .build();

        SegmentAircraftDTO segmentAircraftDTO = SegmentAircraftDTO.builder()
                .code("1215")
                .build();

        SegmentDTO segmentDTO = SegmentDTO.builder()
                .arrival(arrivalDTO)
                .departure(departureDTO)
                .blacklistedInEU(false)
                .id("123")
                .number("AV")
                .numberOfStops(1)
                .duration("11H")
                .carrierCode("1D")
                .aircraft(segmentAircraftDTO)
                .build();

        List<SegmentDTO> segmentDTOS = new ArrayList<>();
        segmentDTOS.add(segmentDTO);

        ItineraryDTO itineraryDTO = ItineraryDTO.builder()
                .duration("PT15H25M")
                .segments(segmentDTOS)
                .build();

        ViajeDTO viajeDTO = ViajeDTO.builder()
                .price(priceDTO)
                .itineraryDTO(itineraryDTO)
                .numberOfBookableSeats(5)
                .dictionaries(dictionaries)
                .build();
        return viajeDTO;
    }

}