package org.web.ex2.domain.model;

import java.util.List;
import java.util.UUID;

public class Order {
    private final String id;
    private final List<OrderItem> items;
    private OrderStatus status;

    public Order(List<OrderItem> items) {
        this.id = UUID.randomUUID().toString();
        this.items = items;
        this.status = OrderStatus.CREATED;
    }

    public String getId() {
        return id;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void orderSent() {
        this.status = OrderStatus.SENT;
    }

    public void orderConfirmed() {
        this.status = OrderStatus.CONFIRMED;
    }

    public void orderReceived() {
        this.status = OrderStatus.RECEIVED;
    }

    public void orderRejected() {
        this.status = OrderStatus.REJECTED;
    }
}