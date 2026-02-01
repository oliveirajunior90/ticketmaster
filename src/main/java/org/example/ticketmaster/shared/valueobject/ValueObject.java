package org.example.ticketmaster.shared.valueobject;

public interface ValueObject<T> {

    boolean sameValueAs(T other);
}
