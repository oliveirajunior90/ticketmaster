package org.example.ticketmaster.order.application.createorder;

import org.example.ticketmaster.event.Event;
import org.example.ticketmaster.event.EventRepository;
import org.example.ticketmaster.order.Order;
import org.example.ticketmaster.order.OrderRepository;
import org.example.ticketmaster.order.event.OrderCreatedEvent;
import org.example.ticketmaster.shared.valueobject.OrderId;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class CreateOrderImpl implements CreateOrder {

    private final OrderRepository orderRepository;
    private final EventRepository eventRepository;
    private final ApplicationEventPublisher eventPublisher;

    public CreateOrderImpl(OrderRepository orderRepository,
                           ApplicationEventPublisher eventPublisher,
                           EventRepository eventRepository
    ) {
        this.orderRepository = orderRepository;
        this.eventPublisher = eventPublisher;
        this.eventRepository = eventRepository;
    }

    @Transactional
    public void execute(UUID uuid, UUID eventId, Integer quantity) {
        Event event = eventRepository.findById(eventId).orElseThrow(() -> new IllegalArgumentException("Event not found."));
        Order order = new Order(uuid, eventId, quantity);
        orderRepository.save(order);
        OrderCreatedEvent orderCreated = new OrderCreatedEvent(
                new OrderId(order.getId()),
                order.getEventId(),
                order.getQuantity(),
                event.getTicketPrice()
        );
        eventPublisher.publishEvent(orderCreated);
    }
}
