package org.web.ex2.adapter.secondary.notification;

import org.web.ex2.domain.model.Order;
import org.web.ex2.domain.port.secondary.NotificationService;

public class ConsoleNotificationService implements NotificationService {
    @Override
    public void notifySupplier(Order order) {
        System.out.println(" Уведомление поставщику: отправлен заказ #" + order.getId() + " со статусом " + order.getStatus());
    }
}