package org.example.ticketmaster.ticket;

import org.example.ticketmaster.shared.valueobject.OrderId;
import org.example.ticketmaster.ticket.domain.TicketStatusEnum;
import org.example.ticketmaster.ticket.domain.Ticket;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TicketTest {

    @Test
    void reserve_throwsWhenOrderIdIsNull() {
        Ticket ticket = new Ticket();

        assertThrows(IllegalArgumentException.class, () -> ticket.reserve(null));
    }

    @Test
    void reserve_throwsWhenTicketIsNotAvailable() throws Exception {
        Ticket ticket = new Ticket();

        OrderId orderId = new OrderId(UUID.randomUUID());

        assertThrows(IllegalStateException.class, () -> ticket.reserve(orderId));
    }

    @Test
    void reserve_setsOrderIdAndStatus() throws Exception {
        Ticket ticket = new Ticket();
        OrderId orderId = new OrderId(UUID.randomUUID());

        ticket.reserve(orderId);

        OrderId storedOrderId = (OrderId) getField(ticket, "orderId");
        TicketStatusEnum status = (TicketStatusEnum) getField(ticket, "status");

        assertEquals(orderId, storedOrderId);
        assertEquals(TicketStatusEnum.RESERVED, status);
    }

    private static void setField(Object target, String fieldName, Object value) throws Exception {
        Field field = target.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(target, value);
    }

    private static Object getField(Object target, String fieldName) throws Exception {
        Field field = target.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        return field.get(target);
    }
}
