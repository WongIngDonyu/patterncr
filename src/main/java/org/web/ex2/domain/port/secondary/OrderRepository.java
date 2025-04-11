package org.web.ex2.domain.port.secondary;


import org.web.ex2.domain.model.Order;

import java.util.List;

public interface OrderRepository {
    void save(Order order);
    Order findById(String id);
    List<Order> findAll();
}