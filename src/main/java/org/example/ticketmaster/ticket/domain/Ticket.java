package org.example.ticketmaster.ticket.domain;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.example.ticketmaster.shared.Money;
import org.example.ticketmaster.shared.valueobject.OrderId;
import org.example.ticketmaster.shared.valueobject.TicketId;

import java.util.UUID;

@Entity
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @Embedded
    @AttributeOverride(name = "id", column = @Column(name = "ticket_id"))
    private TicketId ticketId;

    private UUID eventId;

    private String seatNumber;

    @Embedded
    private Money price;

    @Embedded
    @AttributeOverride(name = "id", column = @Column(name = "order_id"))
    private OrderId orderId;

    @Enumerated(EnumType.STRING)
    private TicketStatusEnum status;

    public Ticket() {
    }

    public Ticket(UUID eventId, OrderId orderId) {
        this.eventId = eventId;
        this.orderId = orderId;
        this.status = TicketStatusEnum.RESERVED;
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
