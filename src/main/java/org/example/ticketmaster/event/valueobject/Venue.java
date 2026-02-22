package org.example.ticketmaster.event.valueobject;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Column;
import org.example.ticketmaster.shared.valueobject.ValueObject;

import java.util.Objects;

@Embeddable
public class Venue implements ValueObject<Venue> {

    @Column(nullable = false, length = 128)
    private String venueName;

    @Column(length = 128)
    private String venueCity;

    @Column (length = 128)
    private String venueAddress;

    public Venue() { }

    public Venue(String venueName, String venueCity, String venueAddress) {
        if (venueName == null || venueName.isBlank()) throw new IllegalArgumentException("Nome do local é obrigatório");
        if(venueCity == null || venueCity.isBlank()) throw new IllegalArgumentException("Cidade é Obrigatório");
        if(venueAddress == null || venueAddress.isBlank()) throw new IllegalArgumentException("Logradouro é obrigatório");

        this.venueName = venueName;
        this.venueCity = venueCity;
        this.venueAddress = venueAddress;
    }

    public String getName() { return venueName; }
    public String getCity() { return venueCity; }
    public String getAddress() { return venueAddress; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Venue other = (Venue) o;
        return Objects.equals(venueName, other.venueName) && Objects.equals(venueCity, other.venueCity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(venueName, venueCity);
    }

    @Override
    public String toString() {
        return venueName + (venueCity != null ? ", " + venueCity : "");
    }

    public boolean sameValueAs(Venue other) {
        return other.equals(this);
    }
}
