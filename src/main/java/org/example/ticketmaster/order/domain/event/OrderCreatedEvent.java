package org.example.ticketmaster.order.domain.event;

import org.example.ticketmaster.shared.valueobject.OrderId;

import java.util.UUID;

public class OrderCreatedEvent {

    private final OrderId orderId;
    private final UUID eventId;
    private final Integer quantity;

    public OrderCreatedEvent(OrderId orderId, UUID eventId, Integer quantity) {
        this.orderId = orderId;
        this.eventId = eventId;
        this.quantity = quantity;
    }

    public OrderId getOrderId() {
        return orderId;
    }

    public UUID getEventId() {
        return eventId;
    }

    public Integer getQuantity() {
        return quantity;
    }
}
