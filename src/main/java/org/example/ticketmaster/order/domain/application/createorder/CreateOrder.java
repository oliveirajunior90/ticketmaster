package org.example.ticketmaster.order.domain.application.createorder;

import java.util.UUID;

public interface CreateOrder {
    void execute(UUID uuid, UUID eventId, Integer quantity);
}
