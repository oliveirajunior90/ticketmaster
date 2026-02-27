package org.example.ticketmaster.ticket.domain;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.example.ticketmaster.shared.Money;
import org.example.ticketmaster.shared.valueobject.OrderId;

import java.util.UUID;

@Entity
@Table(name = "tickets")
public class Ticket {

    @Id
    @GeneratedValue
    private UUID id;

    private UUID eventId;

    private String seatNumber;

    @Embedded
    @AttributeOverride(name = "amount", column = @Column(name = "price_amount"))
    private Money price;

    @Embedded
    @AttributeOverride(name = "id", column = @Column(name = "order_id"))
    private OrderId orderId;

    @Enumerated(EnumType.STRING)
    private TicketStatusEnum status;

    public Ticket() {
    }

    public Ticket(UUID eventId, OrderId orderId, Money price) {
        this.eventId = eventId;
        this.orderId = orderId;
        this.status = TicketStatusEnum.RESERVED;
        this.price = price;
    }

    public boolean isAvailable() {
        return status == TicketStatusEnum.AVAILABLE;
    }

    public UUID getId() {
        return id;
    }

    public Money getPrice() {
        return price;
    }

    public UUID getEventId() {
        return eventId;
    }
}
