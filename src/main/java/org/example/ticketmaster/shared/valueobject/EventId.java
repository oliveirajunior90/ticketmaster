package org.example.ticketmaster.shared.valueobject;

import jakarta.persistence.Embeddable;
import org.example.ticketmaster.event.Event;

import java.util.UUID;

@Embeddable
public final class EventId extends Identifier<Event, UUID> {

    public EventId(UUID id) {
        super(id);
    }

    protected EventId() {}
}
