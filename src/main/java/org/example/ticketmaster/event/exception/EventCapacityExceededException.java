package org.example.ticketmaster.event.exception;

import org.example.ticketmaster.shared.exception.BusinessException;

import java.util.UUID;

public class EventCapacityExceededException extends BusinessException {

    private static final String ERROR_CODE = "EVENT_001";

    public EventCapacityExceededException(UUID eventId, int requestedQuantity, int availableCapacity) {
        super(
            String.format("Event %s does not have enough capacity. Requested: %d, Available: %d",
                eventId, requestedQuantity, availableCapacity),
            ERROR_CODE
        );
    }

    public EventCapacityExceededException(String message) {
        super(message, ERROR_CODE);
    }
}
