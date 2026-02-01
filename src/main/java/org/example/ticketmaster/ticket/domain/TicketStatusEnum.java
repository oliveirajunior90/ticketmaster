package org.example.ticketmaster.ticket.domain;

public enum TicketStatusEnum {

    /**
     * O ticket está disponível para venda.
     */
    AVAILABLE,

    /**
     * O ticket está reservado temporariamente (hold),
     * geralmente com TTL no Redis ou bloqueio otimista no banco.
     */
    RESERVED,

    /**
     * O ticket foi vendido e não pode mais voltar ao estoque.
     */
    SOLD,

    /**
     * O ticket foi cancelado após pagamento falhar
     * ou ordem expirar. Pode voltar para AVAILABLE.
     */
    CANCELLED;
}
