package org.example.ticketmaster.event;

import org.example.ticketmaster.event.valueobject.Venue;
import org.example.ticketmaster.shared.Money;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/events")
public class EventController {

    private final EventRepository eventRepository;

    public EventController(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @GetMapping
    public List<EventResponse> listEvents() {
        return eventRepository.findAll().stream().map(EventController::toResponse).toList();
    }

    @GetMapping("/ping")
    public String ping() {
        return "ok";
    }

    private static EventResponse toResponse(Event event) {
        Money price = event.getTicketPrice();
        Venue venue = event.getVenue();
        return new EventResponse(
            event.getId(),
            event.getName(),
            event.getArtist(),
            venue == null ? null : venue.getName(),
            venue == null ? null : venue.getCity(),
            venue == null ? null : venue.getAddress(),
            event.getStartDate(),
            event.getEndDate(),
            event.getSalesStart(),
            event.getSalesEnd(),
            event.getCapacity(),
            price == null ? null : price.getValue(),
            event.getCreatedAt(),
            event.getStatus()
        );
    }

    public record EventResponse(
        UUID id,
        String name,
        String artist,
        String venueName,
        String venueCity,
        String venueAddress,
        LocalDateTime startDate,
        LocalDateTime endDate,
        LocalDateTime salesStart,
        LocalDateTime salesEnd,
        Integer capacity,
        BigDecimal ticketPrice,
        LocalDateTime createdAt,
        EventStatusEnum status
    ) {}
}
