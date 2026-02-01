package org.example.ticketmaster.shared.valueobject;

import jakarta.persistence.Embeddable;
import org.example.ticketmaster.ticket.domain.Ticket;

@Embeddable
public final class TicketId extends Identifier<Ticket, Long> {

    public TicketId(Long id) {
        super(id);
    }

    protected TicketId() {}
}
