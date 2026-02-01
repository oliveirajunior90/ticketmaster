package org.example.ticketmaster.order.domain;

import org.example.ticketmaster.shared.Money;
import org.example.ticketmaster.shared.valueobject.OrderId;
import org.testcontainers.shaded.org.apache.commons.lang3.builder.Builder;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.springframework.test.util.ReflectionTestUtils.setField;

public class OrderBuilder {

    private OrderId id;
    private UUID customerId;
    private UUID eventId;
    private Integer quantity;
    private Money total;

    public OrderBuilder() {
        this.id = new OrderId(UUID.randomUUID());
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

    public OrderBuilder withId(OrderId id) {
        this.id = id;
        return this;
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
