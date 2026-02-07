package org.example.ticketmaster.order;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.example.ticketmaster.shared.Money;
import org.example.ticketmaster.shared.valueobject.EventId;
import org.example.ticketmaster.shared.valueobject.OrderId;

import java.util.UUID;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    private Integer identifier;

    @Embedded
    @AttributeOverride(name = "id", column = @Column(name = "order_id", nullable = false))
    private OrderId id;

    @Embedded
    @AttributeOverride(name = "id", column = @Column(name = "event_id", nullable = false))
    private EventId eventId;

    private UUID customerId;

    private Integer quantity;

    @Embedded
    private Money total;

    public Order(UUID customerId, UUID eventId, Integer quantity) {
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
