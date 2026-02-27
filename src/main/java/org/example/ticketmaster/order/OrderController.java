package org.example.ticketmaster.order;

import org.example.ticketmaster.order.application.createorder.CreateOrder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final CreateOrder createOrder;

    public OrderController(CreateOrder createOrder) {
        this.createOrder = createOrder;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> create(@RequestBody CreateOrderRequest request) {
        UUID customerId = request.customerId() == null ? UUID.randomUUID() : request.customerId();
        createOrder.execute(customerId, request.eventId(), request.quantity());
        return ResponseEntity.ok().build();
    }

    public record CreateOrderRequest(
        UUID eventId,
        Integer quantity,
        UUID customerId
    ) {}

}
