package org.example.ticketmaster.event;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.example.ticketmaster.event.valueobject.Venue;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "event")
public class Event {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String artist;

    @Embedded
    private Venue venue;

    @Column(nullable = false)
    private LocalDateTime startDate;

    @Column(nullable = false)
    private LocalDateTime endDate;

    @Column(nullable = false)
    private LocalDateTime salesStart;

    @Column(nullable = false)
    private LocalDateTime salesEnd;

    @Column(nullable = false)
    private Integer capacity;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EventStatusEnum status; // DRAFT, ACTIVE, SUSPENDED, SOLD_OUT, FINISHED

    public Event() {}

    public Event(String name, String artist, Venue venue, LocalDateTime startDate,
                 LocalDateTime endDate, LocalDateTime salesStart, LocalDateTime salesEnd, Integer capacity) {
        this.name = name;
        this.artist = artist;
        this.venue = venue;
        this.startDate = startDate;
        this.endDate = endDate;
        this.salesStart = salesStart;
        this.salesEnd = salesEnd;
        this.capacity = capacity;
        this.status = EventStatusEnum.DRAFT;
    }

    public void checkCapacity() {
        if (capacity == null || capacity <= 0) {
            throw new IllegalStateException("Event has no remaining capacity.");
        }
    }

    public void reserveCapacity(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero.");
        }
        if (capacity == null || capacity < quantity) {
            throw new IllegalStateException("Event has no remaining capacity.");
        }
        capacity -= quantity;
        if (capacity == 0) {
            status = EventStatusEnum.SOLD_OUT;
        }
    }

    public UUID getId() {
        return id;
    }
}
