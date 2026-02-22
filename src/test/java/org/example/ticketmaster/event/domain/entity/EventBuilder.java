package org.example.ticketmaster.event.domain.entity;

import org.example.ticketmaster.event.Event;
import org.example.ticketmaster.event.EventStatusEnum;
import org.example.ticketmaster.event.valueobject.Venue;
import org.example.ticketmaster.shared.Money;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class EventBuilder {

    private UUID id;
    private String name;
    private String artist;
    private Venue venue;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private LocalDateTime salesStart;
    private LocalDateTime salesEnd;
    private Integer capacity;
    private EventStatusEnum status;
    private Money ticketPrice;

    public EventBuilder() {
        this.id = null;
        this.artist = "Artist-1";
        this.venue = null;
        this.startDate = LocalDateTime.now();
        this.endDate = LocalDateTime.now().plusDays(5);
        this.salesStart = LocalDateTime.now().minusDays(5);
        this.salesEnd = LocalDateTime.now().minusDays(1);
        this.capacity = 1000;
        this.ticketPrice = new Money(new BigDecimal("50.00"));
        this.status = null;
    }

    public Event build() throws NoSuchFieldException, IllegalAccessException {

        Event event = new Event(name, artist, venue, startDate, endDate, salesStart, salesEnd, capacity, ticketPrice);

        if(status != null) {
            event
                    .getClass()
                    .getDeclaredField("status")
                    .set(event, status);
        }

        if(id != null) {
            event
                    .getClass()
                    .getDeclaredField("id")
                    .set(event, id);
        }

        return event;
    }

    public EventBuilder withId(UUID id) {
        this.id = id;
        return this;
    }

    public EventBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public EventBuilder withArtist(String artist) {
        this.artist = artist;
        return this;
    }

    public EventBuilder withVenue(Venue venue) {
        this.venue = venue;
        return this;
    }

    public EventBuilder withStatus(EventStatusEnum status) {
        this.status = status;
        return this;
    }

    public EventBuilder withTicketPrice(Money ticketPrice) {
        this.ticketPrice = ticketPrice;
        return this;
    }

    public EventBuilder withCapacity(Integer capacity) {
        this.capacity = capacity;
        return this;
    }

    public EventBuilder withSalesStartAt(LocalDateTime salesStart) {
        this.salesStart = salesStart;
        return this;
    }

    public EventBuilder withSalesEndAt(LocalDateTime salesEnd) {
        this.salesEnd = salesEnd;
        return this;
    }

    public EventBuilder withStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
        return this;
    }

    public EventBuilder withEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
        return this;
    }

}
