package org.web.ex2.domain.port.primary;

import org.web.ex2.domain.model.Order;

import java.util.List;

public interface OrderManagementUseCase {
    Order createOrder(List<String> productNames, List<Integer> quantities);
    void sendOrder(String orderId);
    void confirmOrder(String orderId);
    void receiveOrder(String orderId);
    void rejectOrder(String orderId);
    List<Order> getAllOrders();
}
