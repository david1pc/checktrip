package com.proyecto.checktrip.services;

import com.proyecto.checktrip.dto.*;
import com.proyecto.checktrip.entities.*;
import com.proyecto.checktrip.exceptions.ClienteNoExiste;
import com.proyecto.checktrip.exceptions.PersonaNoExiste;
import com.proyecto.checktrip.repo.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class ClienteViajeServiceImpl implements ClienteViajeService{
    private final SegmentMapperDTO segmentMapperDTO;
    private final CarriersMapperDTO carriersMapperDTO;
    private final AircraftMapperDTO aircraftMapperDTO;
    private final PersonRepo personRepo;
    private final ClienteIdaViajesRepo clienteIdaViajesRepo;
    private final ClienteIdaVueltaViajesRepo clienteIdaVueltaViajesRepo;
    private final ClientRepo clientRepo;
    private final ViajeRepo viajeRepo;

    private final ItineraryRepo itineraryRepo;

    private final SegmentRepo segmentRepo;

    private final SegmentAircraftRepo segmentAircraftRepo;

    private final ArrivalRepo arrivalRepo;

    private final PriceRepo priceRepo;

    private final DictionariesRepo dictionariesRepo;

    private final CarriersRepo carriersRepo;

    private final AircraftRepo aircraftRepo;

    @Override
    public ItineraryClientDTO guardarItinerario(String username, ViajeDTO viajeDTO) {
        Person person = this.buscarPersona(username);
        Client client = this.buscarCliente(person.getCodigo());

        List<Aircraft> listAircrafts = new ArrayList<>();
        List<Carriers> listCarriers = new ArrayList<>();

        viajeDTO.dictionaries().aircraft().forEach((aircraftDTO -> {
            Aircraft aircraft = this.aircraftRepo.findAircraftById(aircraftDTO.id()).orElse(null);
            if(aircraft == null){
                aircraft = this.aircraftRepo.save(Aircraft.builder()
                        .id(aircraftDTO.id())
                        .name(aircraftDTO.name())
                        .build());
            }
            listAircrafts.add(aircraft);
        }));

        viajeDTO.dictionaries().carriers().forEach((carriersDTO -> {
            Carriers carriers = this.carriersRepo.findCarriersById(carriersDTO.id()).orElse(null);
            if(carriers == null){
                carriers = this.carriersRepo.save(Carriers.builder()
                        .name(carriersDTO.name())
                        .id(carriersDTO.id())
                        .build());
            }
            listCarriers.add(carriers);
        }));

        Dictionaries dictionaries = Dictionaries.builder()
                .aircrafts(listAircrafts)
                .carriers(listCarriers)
                .build();

        Dictionaries dictionaries1 = dictionariesRepo.save(dictionaries);

        listAircrafts.forEach(aircraft -> {
            aircraft.setDictionaries(dictionaries1);
            this.aircraftRepo.save(aircraft);
        });

        listCarriers.forEach(carriers -> {
            carriers.setDictionaries(dictionaries1);
            this.carriersRepo.save(carriers);
        });

        List<Segment> segments = new ArrayList<>();

        viajeDTO.itineraryDTO().segments().forEach(segmento -> {
            Arrival arrival = this.saveArrival(segmento.arrival());
            Arrival departure = this.saveArrival(segmento.departure());
            SegmentAircraft segmentAircraft = this.saveAircraft(segmento.aircraft());

            Segment segment = Segment.builder()
                    .blackListedInEU(segmento.blacklistedInEU())
                    .duration(segmento.duration())
                    .departure(departure)
                    .arrival(arrival)
                    .id(segmento.id())
                    .number(segmento.number())
                    .carrierCode(segmento.carrierCode())
                    .numberOfStops(segmento.numberOfStops())
                    .arrival(arrival)
                    .departure(departure)
                    .aircraft(segmentAircraft)
                    .build();
            Segment segment1 = this.segmentRepo.save(segment);
            segments.add(segment1);
        });
        Itinerary itinerary = Itinerary.builder()
                .duration(viajeDTO.itineraryDTO().duration())
                .segments(segments)
                .build();

        Itinerary itinerary1 = itineraryRepo.save(itinerary);

        segments.forEach(segment -> {
            segment.setItinerary(itinerary1);
            this.segmentRepo.save(segment);
        });

        Price price = Price.builder()
                .base(viajeDTO.price().base())
                .currency(viajeDTO.price().currency())
                .grandTotal(viajeDTO.price().grandTotal())
                .total(viajeDTO.price().total())
                .build();
        Price price1 = priceRepo.save(price);


        Viaje viaje = Viaje.builder()
                .itinerary(itinerary1)
                .numberOfBookeableSeats(viajeDTO.numberOfBookableSeats())
                .travelClass(viajeDTO.travelClass())
                .price(price1)
                .build();

        Viaje viaje1 = this.viajeRepo.save(viaje);

        dictionaries1.setViaje(viaje1);
        this.dictionariesRepo.save(dictionaries1);

        viaje1.setDictionaries(dictionaries1);
        this.viajeRepo.save(viaje1);

        itinerary1.setViaje(viaje1);
        this.itineraryRepo.save(itinerary1);

        return ItineraryClientDTO.builder()
                .viaje(viaje1)
                .client(client)
                .fechaCreacion(LocalDateTime.now())
                .build();
    }

    @Override
    public ClienteViajeIdaResponseDTO guardarItinerarioIda(ClienteViajeIdaRequestDTO clienteViajeIdaRequestDTO) {
        ItineraryClientDTO itineraryClientDTO = this.guardarItinerario(clienteViajeIdaRequestDTO.username(), clienteViajeIdaRequestDTO.viaje());
        ClienteIdaViajes clienteIdaViajes = ClienteIdaViajes.builder()
                .viaje(itineraryClientDTO.viaje())
                .client(itineraryClientDTO.client())
                .fechaCreacion(LocalDateTime.now())
                .build();
        this.clienteIdaViajesRepo.save(clienteIdaViajes);
        return ClienteViajeIdaResponseDTO.builder()
                .descripcion("Se ha creado su itinerario de ida correctamente")
                .build();
    }

    @Override
    public ClienteViajeIdaVueltaResponseDTO guardarItinerarioIdaVuelta(ClienteViajeIdaVueltaRequestDTO clienteViajeIdaVueltaRequestDTO) {
        ItineraryClientDTO itineraryClientIdaDTO = this.guardarItinerario(clienteViajeIdaVueltaRequestDTO.username(), clienteViajeIdaVueltaRequestDTO.viajeIda());
        ItineraryClientDTO itineraryClientVueltaDTO = this.guardarItinerario(clienteViajeIdaVueltaRequestDTO.username(), clienteViajeIdaVueltaRequestDTO.viajeVuelta());
        ClienteIdaVueltaViajes clienteIdaVueltaViajes = ClienteIdaVueltaViajes.builder()
                .viajeIda(itineraryClientIdaDTO.viaje())
                .client(itineraryClientIdaDTO.client())
                .viajeVuelta(itineraryClientVueltaDTO.viaje())
                .fechaCreacion(LocalDateTime.now())
                .build();
        this.clienteIdaVueltaViajesRepo.save(clienteIdaVueltaViajes);
        return ClienteViajeIdaVueltaResponseDTO.builder()
                .descripcion("Se ha creado su itinerario de ida y vuelta correctamente")
                .build();
    }

    @Override
    public ItinerariesClientDTO obtenerViajes(String username) {
        Person person = this.buscarPersona(username);
        Client client = this.buscarCliente(person.getCodigo());

        List<ClienteIdaViajes> viajesIda = client.getClienteIdaViajes();
        List<ClienteIdaVueltaViajes> viajesIdaVuelta = client.getClienteIdaVueltaViajes();

        List<ClienteViajeIdaRequestDTO> viajesIdaDto = new ArrayList<>();
        List<ClienteViajeIdaVueltaRequestDTO> viajesIdaVueltaDto = new ArrayList<>();

        viajesIda.forEach(clienteViajeIda -> {
            List<SegmentDTO> segmentsDTOs = clienteViajeIda.getViaje().getItinerary().getSegments().stream().map(segmentMapperDTO).toList();
            ItineraryDTO itineraryDTO = ItineraryDTO.builder()
                    .duration(clienteViajeIda.getViaje().getItinerary().getDuration())
                    .segments(segmentsDTOs)
                    .build();
            ViajeDTO viajeDTO = ViajeDTO.builder()
                    .price(PriceDTO.builder()
                            .grandTotal(clienteViajeIda.getViaje().getPrice().getGrandTotal())
                            .currency(clienteViajeIda.getViaje().getPrice().getCurrency())
                            .total(clienteViajeIda.getViaje().getPrice().getTotal())
                            .base(clienteViajeIda.getViaje().getPrice().getBase())
                            .build())
                    .dictionaries(DictionariesDTO.builder()
                            .aircraft(clienteViajeIda.getViaje().getDictionaries().getAircrafts().stream().map(aircraftMapperDTO).toList())
                            .carriers(clienteViajeIda.getViaje().getDictionaries().getCarriers().stream().map(carriersMapperDTO).toList())
                            .build())
                    .itineraryDTO(itineraryDTO)
                    .travelClass(clienteViajeIda.getViaje().getTravelClass())
                    .numberOfBookableSeats(clienteViajeIda.getViaje().getNumberOfBookeableSeats())
                    .build();
            viajesIdaDto.add(ClienteViajeIdaRequestDTO.builder()
                    .viaje(viajeDTO)
                    .fechaCreacion(clienteViajeIda.getFechaCreacion())
                    .username(clienteViajeIda.getClient().getPersona().getUsername())
                    .build());
        });

        viajesIdaVuelta.forEach(clienteViajeIdaVuelta -> {
            List<SegmentDTO> segmentsIdaDTOs = clienteViajeIdaVuelta.getViajeIda().getItinerary().getSegments().stream().map(segmentMapperDTO).toList();
            List<SegmentDTO> segmentsVueltaDTOs = clienteViajeIdaVuelta.getViajeVuelta().getItinerary().getSegments().stream().map(segmentMapperDTO).toList();
            ItineraryDTO itineraryIdaDTO = ItineraryDTO.builder()
                    .duration(clienteViajeIdaVuelta.getViajeIda().getItinerary().getDuration())
                    .segments(segmentsIdaDTOs)
                    .build();

            ItineraryDTO itineraryVueltaDTO = ItineraryDTO.builder()
                    .duration(clienteViajeIdaVuelta.getViajeVuelta().getItinerary().getDuration())
                    .segments(segmentsVueltaDTOs)
                    .build();

            ViajeDTO viajeIdaDTO = ViajeDTO.builder()
                    .price(PriceDTO.builder()
                            .grandTotal(clienteViajeIdaVuelta.getViajeIda().getPrice().getGrandTotal())
                            .currency(clienteViajeIdaVuelta.getViajeIda().getPrice().getCurrency())
                            .total(clienteViajeIdaVuelta.getViajeIda().getPrice().getTotal())
                            .base(clienteViajeIdaVuelta.getViajeIda().getPrice().getBase())
                            .build())
                    .dictionaries(DictionariesDTO.builder()
                            .aircraft(clienteViajeIdaVuelta.getViajeIda().getDictionaries().getAircrafts().stream().map(aircraftMapperDTO).toList())
                            .carriers(clienteViajeIdaVuelta.getViajeIda().getDictionaries().getCarriers().stream().map(carriersMapperDTO).toList())
                            .build())
                    .itineraryDTO(itineraryIdaDTO)
                    .travelClass(clienteViajeIdaVuelta.getViajeIda().getTravelClass())
                    .numberOfBookableSeats(clienteViajeIdaVuelta.getViajeIda().getNumberOfBookeableSeats())
                    .build();

            ViajeDTO viajeVueltaDTO = ViajeDTO.builder()
                    .price(PriceDTO.builder()
                            .grandTotal(clienteViajeIdaVuelta.getViajeVuelta().getPrice().getGrandTotal())
                            .currency(clienteViajeIdaVuelta.getViajeVuelta().getPrice().getCurrency())
                            .total(clienteViajeIdaVuelta.getViajeVuelta().getPrice().getTotal())
                            .base(clienteViajeIdaVuelta.getViajeVuelta().getPrice().getBase())
                            .build())
                    .dictionaries(DictionariesDTO.builder()
                            .aircraft(clienteViajeIdaVuelta.getViajeVuelta().getDictionaries().getAircrafts().stream().map(aircraftMapperDTO).toList())
                            .carriers(clienteViajeIdaVuelta.getViajeVuelta().getDictionaries().getCarriers().stream().map(carriersMapperDTO).toList())
                            .build())
                    .itineraryDTO(itineraryVueltaDTO)
                    .numberOfBookableSeats(clienteViajeIdaVuelta.getViajeVuelta().getNumberOfBookeableSeats())
                    .travelClass(clienteViajeIdaVuelta.getViajeVuelta().getTravelClass())
                    .build();

            viajesIdaVueltaDto.add(ClienteViajeIdaVueltaRequestDTO.builder()
                    .viajeIda(viajeIdaDTO)
                    .viajeVuelta(viajeVueltaDTO)
                    .fechaCreacion(clienteViajeIdaVuelta.getFechaCreacion())
                    .username(clienteViajeIdaVuelta.getClient().getPersona().getUsername())
                    .build());
        });

        return ItinerariesClientDTO.builder()
                .viajesIda(viajesIdaDto)
                .viajesVuelta(viajesIdaVueltaDto)
                .build();
    }

    private SegmentAircraft saveAircraft(SegmentAircraftDTO segmentAircraftDTO){
        return this.segmentAircraftRepo.save(SegmentAircraft.builder()
                        .code(segmentAircraftDTO.code())
                .build());
    }

    private Arrival saveArrival(ArrivalDTO arrival){
        Arrival newArrival = arrivalRepo.findArrivalByIataCode(arrival.iataCode()).orElse(null);
        if (newArrival == null){
            Arrival arrival1 = Arrival.builder()
                    .at(arrival.at())
                    .iataCode(arrival.iataCode())
                    .terminal(arrival.terminal())
                    .build();
            newArrival = this.arrivalRepo.save(arrival1);
        }
        return newArrival;
    }

    private Client buscarCliente(Integer personId){
        return this.clientRepo.findClientByPersonId(personId).orElseThrow(() -> {
            throw new ClienteNoExiste("El cliente no existe o esta inactivo");
        });
    }

    private Person buscarPersona(String username){
        return this.personRepo.findByUsername(username).orElseThrow(() -> {
            throw new PersonaNoExiste("La persona no existe");
        });
    }
}
