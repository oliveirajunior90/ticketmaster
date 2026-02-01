package org.example.ticketmaster.ticket.domain.application;

import org.example.ticketmaster.event.Event;
import org.example.ticketmaster.event.EventRepository;
import org.example.ticketmaster.order.domain.event.OrderCreatedEvent;
import org.example.ticketmaster.ticket.domain.Ticket;
import org.example.ticketmaster.ticket.domain.TicketRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Service
public class CreateTicketOnOrderCreated {

    private final EventRepository eventRepository;
    private final TicketRepository ticketRepository;

    public CreateTicketOnOrderCreated(EventRepository eventRepository,
                                      TicketRepository ticketRepository) {
        this.eventRepository = eventRepository;
        this.ticketRepository = ticketRepository;
    }

    @Transactional
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void handle(OrderCreatedEvent orderHandler) {
        if (orderHandler.getQuantity() == null || orderHandler.getQuantity() <= 0) {
            return;
        }

        Event event = eventRepository.findById(orderHandler.getEventId())
            .orElseThrow(() -> new IllegalArgumentException("Event not found."));

        event.reserveCapacity(orderHandler.getQuantity());
        eventRepository.save(event);

        for (int i = 0; i < orderHandler.getQuantity(); i++) {
            Ticket ticket = new Ticket(orderHandler.getEventId(), orderHandler.getOrderId());
            ticketRepository.save(ticket);
        }
    }
}
