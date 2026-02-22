package org.example.ticketmaster.event;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.example.ticketmaster.event.exception.EventCapacityExceededException;
import org.example.ticketmaster.event.exception.InvalidEventCapacityException;
import org.example.ticketmaster.event.valueobject.Venue;
import org.example.ticketmaster.order.exception.InvalidOrderQuantityException;
import org.example.ticketmaster.shared.Money;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
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

    @Column(nullable = false)
    private Money ticketPrice;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EventStatusEnum status; // DRAFT, ACTIVE, SUSPENDED, SOLD_OUT, FINISHED

    public Event() {}

    public Event(String name, String artist, Venue venue, LocalDateTime startDate,
                 LocalDateTime endDate, LocalDateTime salesStart, LocalDateTime salesEnd, Integer capacity, Money ticketPrice) {
        this.name = name;
        this.artist = artist;
        this.venue = venue;
        this.startDate = startDate;
        this.endDate = endDate;
        this.salesStart = salesStart;
        this.salesEnd = salesEnd;
        this.capacity = capacity;
        this.ticketPrice = ticketPrice;
        this.status = EventStatusEnum.DRAFT;
        this.createdAt = LocalDateTime.now();
    }

    public void checkCapacity() {
        if (capacity == null || capacity <= 0) {
            throw new InvalidEventCapacityException(capacity);
        }
    }

    public void reserveCapacity(int quantity) {
        if (quantity <= 0) {
            throw new InvalidOrderQuantityException(quantity);
        }
        if (capacity == null || capacity < quantity) {
            throw new EventCapacityExceededException(this.id, quantity, capacity != null ? capacity : 0);
        }
        capacity -= quantity;
        if (capacity == 0) {
            status = EventStatusEnum.SOLD_OUT;
        }
    }

    public Money getTicketPrice() {
        return ticketPrice;
    }

    public UUID getId() {
        return id;
    }

    // Package-private methods for testing purposes
    void setId(UUID id) {
        this.id = id;
    }

    void setStatus(EventStatusEnum status) {
        this.status = status;
    }
}
