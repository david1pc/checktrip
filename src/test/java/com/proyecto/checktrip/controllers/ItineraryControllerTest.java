package com.proyecto.checktrip.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.proyecto.checktrip.dto.*;
import com.proyecto.checktrip.entities.Viaje;
import com.proyecto.checktrip.repo.*;
import com.proyecto.checktrip.services.ClientService;
import com.proyecto.checktrip.services.ClienteViajeService;
import com.proyecto.checktrip.services.ClienteViajeServiceImpl;
import com.proyecto.checktrip.services.RoleServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = ItineraryController.class, excludeAutoConfiguration = SecurityAutoConfiguration.class)
@ActiveProfiles("test")
class ItineraryControllerTest {
    @MockBean
    private ClienteViajeServiceImpl clienteViajeService;
    @MockBean
    private ClientService clientService;
    @MockBean
    private RoleServiceImpl roleService;
    @MockBean
    private PersonRepo personRepo;
    @MockBean
    private ClientRepo clientRepo;
    @MockBean
    private ViajeRepo viajeRepo;
    @MockBean
    private ItineraryRepo itineraryRepo;
    @MockBean
    private SegmentRepo segmentRepo;
    @MockBean
    private SegmentAircraftRepo segmentAircraftRepo;
    @MockBean
    private ArrivalRepo arrivalRepo;
    @MockBean
    private PriceRepo priceRepo;
    @MockBean
    private DictionariesRepo dictionariesRepo;
    @MockBean
    private CarriersRepo carriersRepo;
    @MockBean
    private AircraftRepo aircraftRepo;
    @MockBean
    private PasswordEncoder passwordEncoder;
    @MockBean
    private RoleRepo roleRepo;
    @MockBean
    private RoleClientRepo roleClientRepo;
    @Autowired
    private MockMvc mockMvc;


    @Test
    void createItineraryIda() throws Exception {
        ViajeDTO viajeDTO = this.retornarViaje();

        ClienteViajeIdaRequestDTO clienteViajeIdaRequestDTO = ClienteViajeIdaRequestDTO.builder()
                .username("david2")
                .viaje(viajeDTO)
                .fechaCreacion(LocalDateTime.now())
                .build();

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        RequestBuilder request = MockMvcRequestBuilders
                .post("/api/itinerary/ida")
                .accept(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(clienteViajeIdaRequestDTO))
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(request)
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void createItineraryIdaVuelta() throws Exception {
        ViajeDTO viajeIdaDTO = this.retornarViaje();
        ViajeDTO viajeVueltaDTO = this.retornarViaje();
        ClienteViajeIdaVueltaRequestDTO clienteViajeIdaVueltaRequestDTO = ClienteViajeIdaVueltaRequestDTO.builder()
                .username("david2")
                .viajeIda(viajeIdaDTO)
                .viajeVuelta(viajeVueltaDTO)
                .fechaCreacion(LocalDateTime.now())
                .build();

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        RequestBuilder request = MockMvcRequestBuilders
                .post("/api/itinerary/ida-vuelta")
                .accept(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(clienteViajeIdaVueltaRequestDTO))
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(request)
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void findAllItineraries() throws Exception {
        String username = "davidpc";

        ObjectMapper mapper = new ObjectMapper();

        RequestBuilder request = MockMvcRequestBuilders
                .post("/api/itinerary/")
                .accept(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(username))
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(request)
                .andExpect(status().isOk())
                .andReturn();
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

        return ViajeDTO.builder()
                .price(priceDTO)
                .itineraryDTO(itineraryDTO)
                .numberOfBookableSeats(5)
                .dictionaries(dictionaries)
                .build();
    }
}