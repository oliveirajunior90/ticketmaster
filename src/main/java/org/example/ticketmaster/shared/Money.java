package org.example.ticketmaster.shared;

import jakarta.persistence.Embeddable;
import org.example.ticketmaster.shared.valueobject.ValueObject;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

@Embeddable
public class Money implements ValueObject<Money> {
    private BigDecimal amount;

    public Money(BigDecimal amount) {
        this.amount = amount;
    }

    public Money() {

    }

    @Override
    public boolean sameValueAs(Money other) {
        return other != null && Objects.equals(this.amount, other.amount);
    }

    public BigDecimal getValue() {
        return amount;
    }

    public Money multiply(BigDecimal multiplier) {
        return new Money(amount.multiply(multiplier));
    }

    public Money add(Money other) {
        return new Money(amount.add(other.amount));
    }

    public Money subtract(Money other) {
        return new Money(amount.subtract(other.amount));
    }

    public Money divide(BigDecimal divisor, RoundingMode roundingMode) {
        return new Money(amount.divide(divisor, Objects.isNull(roundingMode) ? RoundingMode.FLOOR : roundingMode));
    }

    // equals e hashCode usando amount e currency
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Money money = (Money) o;
        return sameValueAs(money);
    }
}
