package org.example.ticketmaster.order.application.createorder;

import java.util.UUID;

public interface CreateOrder {
    void execute(UUID uuid, UUID eventId, Integer quantity);
}
