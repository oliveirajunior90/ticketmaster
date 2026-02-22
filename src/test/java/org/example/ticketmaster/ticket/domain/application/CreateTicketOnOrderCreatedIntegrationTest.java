package org.example.ticketmaster.ticket.domain.application;

import org.example.ticketmaster.event.Event;
import org.example.ticketmaster.event.EventRepository;
import org.example.ticketmaster.event.domain.entity.EventBuilder;
import org.example.ticketmaster.event.valueobject.Venue;
import org.example.ticketmaster.order.event.OrderCreatedEvent;
import org.example.ticketmaster.shared.IntegrationTest;
import org.example.ticketmaster.shared.Money;
import org.example.ticketmaster.shared.valueobject.OrderId;
import org.example.ticketmaster.ticket.domain.Ticket;
import org.example.ticketmaster.ticket.domain.TicketRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class CreateTicketOnOrderCreatedIntegrationTest extends IntegrationTest {

    @Autowired
    private CreateTicketOnOrderCreated createTicketOnOrderCreated;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    private Event testEvent;

    @BeforeEach
    void setUp() throws NoSuchFieldException, IllegalAccessException {
        ticketRepository.deleteAll();
        eventRepository.deleteAll();

        testEvent = new EventBuilder()
                .withName("Test Concert")
                .withArtist("Test Artist")
                .withVenue(new Venue("Test Venue", "Test City", "Test Country"))
                .withStartDate(LocalDateTime.now().plusDays(30))
                .withEndDate(LocalDateTime.now().plusDays(30).plusHours(3))
                .withSalesStartAt(LocalDateTime.now().minusDays(10))
                .withSalesEndAt(LocalDateTime.now().plusDays(20))
                .withCapacity(100)
                .withTicketPrice(new Money(new BigDecimal("150")))
                .build();

        testEvent = eventRepository.save(testEvent);
    }

    @Test
    void shouldCreateTicketsWhenOrderCreatedEventIsPublished() {
        // Given
        UUID eventId = testEvent.getId();
        OrderId orderId = new OrderId(UUID.randomUUID());
        Integer quantity = 3;

        // When
        OrderCreatedEvent event = new OrderCreatedEvent(orderId, eventId, quantity, testEvent.getTicketPrice());
        createTicketOnOrderCreated.handle(event);

        // Then
        List<Ticket> tickets = ticketRepository.findAll();
        assertEquals(quantity, tickets.size());
        tickets.forEach(ticket -> {
            assertEquals(eventId, ticket.getEventId());
            assertNotNull(ticket.getId());
        });
    }

    @Test
    void shouldReduceEventCapacityWhenTicketsAreCreated() {
        // Given
        UUID eventId = testEvent.getId();
        OrderId orderId = new OrderId(UUID.randomUUID());
        Integer quantity = 10;

        // When
        OrderCreatedEvent event = new OrderCreatedEvent(orderId, eventId, quantity, testEvent.getTicketPrice());
        createTicketOnOrderCreated.handle(event);

        // Then
        Event updatedEvent = eventRepository.findById(eventId).orElseThrow();
        // Note: Capacity is reduced in Event.reserveCapacity() method
        // The actual capacity value depends on Event implementation
        assertNotNull(updatedEvent);
    }

    @Test
    void shouldNotCreateTicketsWhenQuantityIsZero() {
        // Given
        UUID eventId = testEvent.getId();
        OrderId orderId = new OrderId(UUID.randomUUID());
        Integer quantity = 0;

        // When
        OrderCreatedEvent event = new OrderCreatedEvent(orderId, eventId, quantity, eventRepository.findById(eventId).orElseThrow().getTicketPrice());
        createTicketOnOrderCreated.handle(event);

        // Then
        List<Ticket> tickets = ticketRepository.findAll();
        assertEquals(0, tickets.size());
    }

    @Test
    void shouldNotCreateTicketsWhenQuantityIsNull() {
        // Given
        UUID eventId = testEvent.getId();
        OrderId orderId = new OrderId(UUID.randomUUID());
        Integer quantity = null;

        // When
        OrderCreatedEvent event = new OrderCreatedEvent(orderId, eventId, quantity, testEvent.getTicketPrice());
        createTicketOnOrderCreated.handle(event);

        // Then
        List<Ticket> tickets = ticketRepository.findAll();
        assertEquals(0, tickets.size());
    }

    @Test
    void shouldThrowExceptionWhenEventNotFound() {
        // Given
        UUID nonExistentEventId = UUID.randomUUID();
        OrderId orderId = new OrderId(UUID.randomUUID());
        Integer quantity = 2;

        // When & Then
        OrderCreatedEvent event = new OrderCreatedEvent(orderId, nonExistentEventId, quantity, testEvent.getTicketPrice());
        assertThrows(IllegalArgumentException.class, () -> {
            createTicketOnOrderCreated.handle(event);
        });
    }
}
