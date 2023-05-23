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
    private final PersonRepo personRepo;
    private final ClienteIdaViajesRepo clienteIdaViajesRepo;
    private final ClienteIdaVueltaViajesRepo clienteIdaVueltaViajesRepo;
    private final ClientRepo clientRepo;
    private final ViajeRepo viajeRepo;

    private final ItineraryRepo itineraryRepo;

    private final SegmentRepo segmentRepo;

    private final SegmentAircraftRepo segmentAircraftRepo;

    private final OperatingRepo operatingRepo;

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

        List<Segment> segments = new ArrayList<>();

        viajeDTO.itineraryDTO().segments().forEach(segmento -> {
            Arrival arrival = this.saveArrival(segmento.arrival());
            Arrival departure = this.saveArrival(segmento.departure());
            Operating operating = this.saveOperating(segmento.operating());
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
                    .operating(operating)
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

        return ItinerariesClientDTO.builder()
                .viajesIda(client.getClienteIdaViajes())
                .viajesVuelta(client.getClienteIdaVueltaViajes())
                .build();
    }

    private SegmentAircraft saveAircraft(SegmentAircraftDTO segmentAircraftDTO){
        return this.segmentAircraftRepo.save(SegmentAircraft.builder()
                        .code(segmentAircraftDTO.code())
                .build());
    }

    private Operating saveOperating(OperatingDTO operatingDTO){
        return this.operatingRepo.save(Operating.builder()
                .carrierCode(operatingDTO.carrierCode())
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
