package org.example.ticketmaster.order.domain.application.createorder;

import org.example.ticketmaster.order.domain.Order;
import org.example.ticketmaster.order.domain.OrderRepository;
import org.example.ticketmaster.order.domain.event.OrderCreatedEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class CreateOrderImpl implements CreateOrder {

    private final OrderRepository orderRepository;
    private final ApplicationEventPublisher eventPublisher;

    public CreateOrderImpl(OrderRepository orderRepository,
                           ApplicationEventPublisher eventPublisher) {
        this.orderRepository = orderRepository;
        this.eventPublisher = eventPublisher;
    }

    @Transactional
    public void execute(UUID uuid, UUID eventId, Integer quantity) {
        Order order = new Order(uuid, eventId, quantity);
        orderRepository.save(order);
        OrderCreatedEvent orderCreated = new OrderCreatedEvent(order.getId(), order.getEventId(), order.getQuantity());
        eventPublisher.publishEvent(orderCreated);
    }
}
