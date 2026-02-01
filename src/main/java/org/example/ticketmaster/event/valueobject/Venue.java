package org.example.ticketmaster.event.valueobject;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Column;
import org.example.ticketmaster.shared.valueobject.ValueObject;

import java.util.Objects;

@Embeddable
public class Venue implements ValueObject<Venue> {

    @Column(nullable = false, length = 128)
    private String venue_name;
    @Column(length = 128)
    private String venue_city;

    public Venue() { }

    public Venue(String venue_name, String venue_city, String venue_address) {
        if (venue_name == null || venue_name.isBlank()) throw new IllegalArgumentException("Nome do local é obrigatório");
        if(venue_city == null || venue_city.isBlank()) throw new IllegalArgumentException("Cidade é Obrigatório");

        this.venue_name = venue_name;
        this.venue_city = venue_city;
    }

    public String getName() { return venue_name; }
    public String getCity() { return venue_city; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Venue other = (Venue) o;
        return Objects.equals(venue_name, other.venue_name) && Objects.equals(venue_city, other.venue_city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(venue_name, venue_city);
    }

    @Override
    public String toString() {
        return venue_name + (venue_city != null ? ", " + venue_city : "");
    }

    public boolean sameValueAs(Venue other) {
        return other.equals(this);
    }
}
