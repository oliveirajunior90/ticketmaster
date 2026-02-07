package org.example.ticketmaster.event.exception;

import org.example.ticketmaster.shared.exception.BusinessException;

public class InvalidEventCapacityException extends BusinessException {

    private static final String ERROR_CODE = "EVENT_002";

    public InvalidEventCapacityException(Integer capacity) {
        super(
            String.format("Invalid event capacity: %d. Capacity must be greater than zero.", capacity),
            ERROR_CODE
        );
    }
}
