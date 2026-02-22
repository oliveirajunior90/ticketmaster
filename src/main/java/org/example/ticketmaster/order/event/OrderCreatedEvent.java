package org.example.ticketmaster.order.event;

import org.example.ticketmaster.shared.Money;
import org.example.ticketmaster.shared.valueobject.OrderId;

import java.util.UUID;

public class OrderCreatedEvent {

    private final OrderId orderId;
    private final UUID eventId;
    private final Integer quantity;
    private Money eventPrice;

    public OrderCreatedEvent(OrderId orderId, UUID eventId, Integer quantity, Money eventPrice) {
        this.orderId = orderId;
        this.eventId = eventId;
        this.quantity = quantity;
        this.eventPrice = eventPrice;
    }

    public OrderId getOrderId() {
        return orderId;
    }

    public UUID getEventId() {
        return eventId;
    }

    public Money getEventPrice() {
        return eventPrice;
    }

    public Integer getQuantity() {
        return quantity;
    }
}
