package org.example.ticketmaster.ticket.domain.exception;

import org.example.ticketmaster.shared.exception.BusinessException;

import java.util.UUID;

public class TicketNotAvailableException extends BusinessException {

    private static final String ERROR_CODE = "TICKET_001";

    public TicketNotAvailableException(UUID ticketId) {
        super(
            String.format("Ticket %s is not available for purchase or reservation.", ticketId),
            ERROR_CODE
        );
    }
}
