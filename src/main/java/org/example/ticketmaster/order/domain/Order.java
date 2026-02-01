package org.example.ticketmaster.order.domain;

import jakarta.persistence.Embedded;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.example.ticketmaster.shared.Money;
import org.example.ticketmaster.shared.valueobject.EventId;
import org.example.ticketmaster.shared.valueobject.OrderId;

import java.util.UUID;

@Entity
@Table(name = "orders")
public class Order {

    @EmbeddedId
    private OrderId id;

    @Embedded
    private EventId eventId;

    private UUID customerId;

    private Integer quantity;

    @Embedded
    private Money total;

    public Order(UUID customerId, UUID eventId, Integer quantity) {
        this.id = new OrderId(UUID.randomUUID());
        this.eventId = new EventId(eventId);
        this.customerId = customerId;
        this.quantity = quantity;
    }

    public Order() {
    }

    public Money getTotal() {
        return total;
    }

    public OrderId getId() {
        return id;
    }

    public UUID getEventId() {
        return eventId == null ? null : eventId.getId();
    }

    public Integer getQuantity() {
        return quantity;
    }

    public UUID getCustomerId() {
        return customerId;
    }
}
