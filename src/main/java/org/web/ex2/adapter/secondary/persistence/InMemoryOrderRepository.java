package org.web.ex2.adapter.secondary.persistence;

import org.web.ex2.domain.model.Order;
import org.web.ex2.domain.port.secondary.OrderRepository;

import java.util.*;

public class InMemoryOrderRepository implements OrderRepository {

    private final Map<String, Order> orders = new HashMap<>();

    @Override
    public void save(Order order) {
        orders.put(order.getId(), order);
    }

    @Override
    public Order findById(String id) {
        Order order = orders.get(id);
        if (order == null) {
            throw new IllegalArgumentException("Заказ не найден: " + id);
        }
        return order;
    }

    @Override
    public List<Order> findAll() {
        return new ArrayList<>(orders.values());
    }
}