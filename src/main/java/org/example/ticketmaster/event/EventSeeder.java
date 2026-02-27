package org.example.ticketmaster.event;

import org.example.ticketmaster.event.valueobject.Venue;
import org.example.ticketmaster.shared.Money;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class EventSeeder implements ApplicationRunner {

    private final EventRepository eventRepository;

    public EventSeeder(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public void run(ApplicationArguments args) {
        if (eventRepository.count() > 0) {
            return;
        }

        LocalDateTime baseDate = LocalDateTime.now().plusDays(7);
        List<Event> events = new ArrayList<>();

        for (int i = 1; i <= 20; i++) {
            LocalDateTime start = baseDate.plusDays(i);
            LocalDateTime end = start.plusHours(2);
            LocalDateTime salesStart = start.minusDays(30);
            LocalDateTime salesEnd = start.minusHours(1);

            Event event = new Event(
                "Evento " + i,
                "Artista " + i,
                new Venue("Arena " + i, "Cidade " + ((i % 5) + 1), "Rua " + i + ", 100"),
                start,
                end,
                salesStart,
                salesEnd,
                3000 + (i * 10),
                new Money(BigDecimal.valueOf(50 + i))
            );

            events.add(event);
        }

        eventRepository.saveAll(events);
    }
}
