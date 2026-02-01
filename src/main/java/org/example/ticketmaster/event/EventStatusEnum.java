package org.example.ticketmaster.event;

public enum EventStatusEnum {

    /**
     * Evento criado, mas ainda não publicado para o público.
     * Somente administradores enxergam.
     */
    DRAFT,

    /**
     * Evento publicado e visível, mas ainda não está no período de venda.
     */
    PUBLISHED,

    /**
     * Ingressos estão abertos para compra.
     * Sistema deve permitir reservas e vendas.
     */
    SALES_OPEN,

    /**
     * As vendas estão temporariamente pausadas.
     * Pode ocorrer por manutenção, falha de sistema, auditoria, etc.
     */
    SALES_PAUSED,

    /**
     * Os ingressos estão esgotados (sold out).
     */
    SOLD_OUT,

    /**
     * As vendas foram encerradas manualmente ou porque atingiu data limite.
     */
    SALES_CLOSED,

    /**
     * O evento está acontecendo neste momento.
     */
    IN_PROGRESS,

    /**
     * O evento já aconteceu.
     */
    COMPLETED,

    /**
     * O evento foi cancelado.
     * O sistema deve bloquear vendas e iniciar processos de estorno se necessário.
     */
    CANCELLED
}
