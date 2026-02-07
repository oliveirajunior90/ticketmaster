package org.example.ticketmaster.order;

import org.example.ticketmaster.shared.Money;

import java.util.UUID;

import static org.springframework.test.util.ReflectionTestUtils.setField;

public class OrderBuilder {

    private UUID customerId;
    private UUID eventId;
    private Integer quantity;
    private Money total;

    public OrderBuilder() {
        this.customerId = UUID.randomUUID();
        this.eventId = UUID.randomUUID();
        this.quantity = 0;
        this.total = null;
    }

    public Order build() {
        Order order = new Order(customerId, eventId, quantity);

        if (total != null) {
            setField(order, "total", total);
        }

        return order;
    }

    public OrderBuilder withCustomerId(UUID customerId) {
        this.customerId = customerId;
        return this;
    }

    public OrderBuilder withEventId(UUID eventId) {
        this.eventId = eventId;
        return this;
    }

    public OrderBuilder withQuantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }

    public OrderBuilder withTotal(Money total) {
        this.total = total;
        return this;
    }

}
