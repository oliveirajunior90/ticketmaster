package org.example.ticketmaster.order.exception;

import org.example.ticketmaster.shared.exception.BusinessException;

public class InvalidOrderQuantityException extends BusinessException {

    private static final String ERROR_CODE = "ORDER_001";

    public InvalidOrderQuantityException(Integer quantity) {
        super(
            String.format("Invalid order quantity: %d. Quantity must be greater than zero.", quantity),
            ERROR_CODE
        );
    }
}
