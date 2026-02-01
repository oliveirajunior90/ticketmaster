package org.example.ticketmaster.event.domain.entity;

import org.example.ticketmaster.event.Event;
import org.example.ticketmaster.event.valueobject.Venue;
import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class EventTest {

    @Test
    void checkCapacity_throwsWhenCapacityIsNull() throws NoSuchFieldException, IllegalAccessException {
        Event event = new EventBuilder().withCapacity(null).build();

        assertThrows(IllegalStateException.class, event::checkCapacity);
    }

    @Test
    void checkCapacity_throwsWhenCapacityIsZero() throws NoSuchFieldException, IllegalAccessException {
        Event event = new EventBuilder().withCapacity(0).build();

        assertThrows(IllegalStateException.class, event::checkCapacity);
    }

    @Test
    void checkCapacity_allowsPositiveCapacity() throws NoSuchFieldException, IllegalAccessException {
        Event event = new EventBuilder().withCapacity(10).build();

        assertDoesNotThrow(event::checkCapacity);
    }
}
