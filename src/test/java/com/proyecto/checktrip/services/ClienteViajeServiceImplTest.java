package com.proyecto.checktrip.services;

import com.proyecto.checktrip.dto.*;
import com.proyecto.checktrip.entities.Role;
import com.proyecto.checktrip.repo.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@DataJpaTest
class ClienteViajeServiceImplTest {
    @Autowired
    private ClienteViajeServiceImpl clienteViajeService;
    @Autowired
    private ClientService clientService;
    @Autowired
    private RoleServiceImpl roleService;
    @Autowired
    private PersonRepo personRepo;
    @Autowired
    private ClientRepo clientRepo;
    @Autowired
    private ViajeRepo viajeRepo;
    @Autowired
    private ItineraryRepo itineraryRepo;
    @Autowired
    private SegmentRepo segmentRepo;
    @Autowired
    private SegmentAircraftRepo segmentAircraftRepo;
    @Autowired
    private OperatingRepo operatingRepo;
    @Autowired
    private ArrivalRepo arrivalRepo;
    @Autowired
    private PriceRepo priceRepo;
    @Autowired
    private DictionariesRepo dictionariesRepo;
    @Autowired
    private CarriersRepo carriersRepo;
    @Autowired
    private AircraftRepo aircraftRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleRepo roleRepo;
    @Autowired
    private RoleClientRepo roleClientRepo;

    /*
    @Test
    public void deberiaCrearItinerario() {
        Role role = new Role("CLIENT");
        roleService.createRole(role);
        PersonDTO person = PersonDTO.builder()
                .username("david2")
                .apellidos("david")
                .nombres("rodriguez")
                .password("12345")
                .correo("david@email.com")
                .build();

        ClientRequestDTO clientRequestDTO = ClientRequestDTO.builder()
                .person(person)
                .build();

        clientService.createClient(clientRequestDTO);
        /*
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

        OperatingDTO operatingDTO = OperatingDTO.builder()
                .carrierCode("1551")
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
                .segmentAircraft(segmentAircraftDTO)
                .operating(operatingDTO)
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

        ClienteViajeIdaRequestDTO clienteViajeIdaRequestDTO = ClienteViajeIdaRequestDTO.builder()
                .username("david2")
                .viaje(viajeDTO)
                .fechaCreacion(LocalDateTime.now())
                .build();
        ClienteViajeIdaResponseDTO clienteViajeIdaResponseDTO = this.clienteViajeService.guardarItinerarioIda(clienteViajeIdaRequestDTO);
        Assertions.assertThat(clienteViajeIdaRequestDTO.username()).isEqualTo(clienteViajeIdaResponseDTO.username());
         */
    //}
}