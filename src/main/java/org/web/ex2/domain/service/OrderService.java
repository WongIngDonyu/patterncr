package org.web.ex2.domain.service;

import org.web.ex2.domain.model.Order;
import org.web.ex2.domain.model.OrderItem;
import org.web.ex2.domain.model.Product;
import org.web.ex2.domain.port.primary.OrderManagementUseCase;
import org.web.ex2.domain.port.secondary.NotificationService;
import org.web.ex2.domain.port.secondary.OrderRepository;
import org.web.ex2.domain.port.secondary.ProductRepository;

import java.util.ArrayList;
import java.util.List;

public class OrderService implements OrderManagementUseCase {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final NotificationService notificationService;

    public OrderService(OrderRepository orderRepository, ProductRepository productRepository, NotificationService notificationService) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.notificationService = notificationService;
    }

    @Override
    public Order createOrder(List<String> productNames, List<Integer> quantities) {
        List<OrderItem> items = new ArrayList<>();
        for (int i = 0; i < productNames.size(); i++) {
            Product product = productRepository.findByName(productNames.get(i));
            items.add(new OrderItem(product, quantities.get(i)));
        }
        Order order = new Order(items);
        orderRepository.save(order);
        return order;
    }

    @Override
    public void sendOrder(String orderId) {
        Order order = orderRepository.findById(orderId);
        order.orderSent();
        notificationService.notifySupplier(order);
        orderRepository.save(order);
    }

    @Override
    public void confirmOrder(String orderId) {
        Order order = orderRepository.findById(orderId);
        order.orderConfirmed();
        orderRepository.save(order);
    }

    @Override
    public void receiveOrder(String orderId) {
        Order order = orderRepository.findById(orderId);
        order.orderReceived();
        orderRepository.save(order);
    }

    @Override
    public void rejectOrder(String orderId) {
        Order order = orderRepository.findById(orderId);
        order.orderRejected();
        orderRepository.save(order);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
}