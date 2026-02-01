package org.example.ticketmaster.ticket.domain;

import org.example.ticketmaster.shared.Money;
import org.example.ticketmaster.shared.valueobject.OrderId;
import org.example.ticketmaster.shared.valueobject.TicketId;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.UUID;

public class TicketBuilder {

    private UUID id;
    private TicketId ticketId;
    private UUID eventId;
    private String seatNumber;
    private Money price;
    private OrderId orderId;
    private TicketStatusEnum status;

    public TicketBuilder() {
        this.id = null;
        this.ticketId = new TicketId(1L);
        this.eventId = UUID.randomUUID();
        this.seatNumber = "A1";
        this.price = new Money(new BigDecimal("50.00"));
        this.orderId = null;
        this.status = TicketStatusEnum.AVAILABLE;
    }

    public Ticket build() throws NoSuchFieldException, IllegalAccessException {
        Ticket ticket = new Ticket();

        if (id != null) {
            setField(ticket, "id", id);
        }

        if (ticketId != null) {
            setField(ticket, "ticketId", ticketId);
        }

        if (eventId != null) {
            setField(ticket, "eventId", eventId);
        }

        if (seatNumber != null) {
            setField(ticket, "seatNumber", seatNumber);
        }

        if (price != null) {
            setField(ticket, "price", price);
        }

        if (orderId != null) {
            setField(ticket, "orderId", orderId);
        }

        if (status != null) {
            setField(ticket, "status", status);
        }

        return ticket;
    }

    public TicketBuilder withId(UUID id) {
        this.id = id;
        return this;
    }

    public TicketBuilder withTicketId(TicketId ticketId) {
        this.ticketId = ticketId;
        return this;
    }

    public TicketBuilder withEventId(UUID eventId) {
        this.eventId = eventId;
        return this;
    }

    public TicketBuilder withSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
        return this;
    }

    public TicketBuilder withPrice(Money price) {
        this.price = price;
        return this;
    }

    public TicketBuilder withPrice(BigDecimal price) {
        this.price = price == null ? null : new Money(price);
        return this;
    }

    public TicketBuilder withOrderId(OrderId orderId) {
        this.orderId = orderId;
        return this;
    }

    public TicketBuilder withStatus(TicketStatusEnum status) {
        this.status = status;
        return this;
    }

    private static void setField(Object target, String fieldName, Object value)
        throws NoSuchFieldException, IllegalAccessException {
        Field field = target.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(target, value);
    }
}
