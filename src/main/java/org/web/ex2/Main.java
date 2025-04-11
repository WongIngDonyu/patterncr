package org.web.ex2;

import org.web.ex2.adapter.primary.console.ConsoleUI;
import org.web.ex2.adapter.secondary.notification.ConsoleNotificationService;
import org.web.ex2.adapter.secondary.persistence.InMemoryOrderRepository;
import org.web.ex2.adapter.secondary.persistence.InMemoryProductRepository;
import org.web.ex2.domain.service.OrderService;

public class Main {
    public static void main(String[] args) {
        var orderRepo = new InMemoryOrderRepository();
        var productRepo = new InMemoryProductRepository();
        var notifier = new ConsoleNotificationService();
        var service = new OrderService(orderRepo, productRepo, notifier);
        var ui = new ConsoleUI(service);
        ui.run();
    }
}