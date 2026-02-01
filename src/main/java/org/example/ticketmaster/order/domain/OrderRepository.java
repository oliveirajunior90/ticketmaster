package org.example.ticketmaster.order.domain;

import org.example.ticketmaster.shared.valueobject.OrderId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, OrderId> {
}


