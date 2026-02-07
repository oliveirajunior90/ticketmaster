package org.example.ticketmaster.shared.valueobject;

import jakarta.persistence.Embeddable;
import org.example.ticketmaster.order.Order;

import java.util.UUID;

@Embeddable
public final class OrderId extends Identifier<Order, UUID> {

    public OrderId(UUID id) {
        super(id);
    }

    protected OrderId() {}
}
