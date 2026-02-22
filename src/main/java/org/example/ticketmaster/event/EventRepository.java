package org.example.ticketmaster.event;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

public interface EventRepository extends JpaRepository<Event, UUID> {
}
