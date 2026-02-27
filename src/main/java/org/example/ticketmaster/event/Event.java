package org.example.ticketmaster.event;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
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
@Table(name = "events")
public class Event {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String artist;

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "venueName", column = @Column(name = "venue_name", nullable = false, length = 128)),
        @AttributeOverride(name = "venueCity", column = @Column(name = "venue_city", length = 128)),
        @AttributeOverride(name = "venueAddress", column = @Column(name = "venue_address", length = 128))
    })
    private Venue venue;

    @Column(name = "start_date", nullable = false)
    private LocalDateTime startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDateTime endDate;

    @Column(name = "sales_start", nullable = false)
    private LocalDateTime salesStart;

    @Column(name = "sales_end", nullable = false)
    private LocalDateTime salesEnd;

    @Column(nullable = false)
    private Integer capacity;

    @Embedded
    @AttributeOverride(name = "amount", column = @Column(name = "ticket_price_amount", nullable = false))
    private Money ticketPrice;

    @Column(name = "created_at", nullable = false)
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

    public String getName() {
        return name;
    }

    public String getArtist() {
        return artist;
    }

    public Venue getVenue() {
        return venue;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public LocalDateTime getSalesStart() {
        return salesStart;
    }

    public LocalDateTime getSalesEnd() {
        return salesEnd;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public EventStatusEnum getStatus() {
        return status;
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
