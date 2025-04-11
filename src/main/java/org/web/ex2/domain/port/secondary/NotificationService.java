package org.web.ex2.domain.port.secondary;

import org.web.ex2.domain.model.Order;

public interface NotificationService {
    void notifySupplier(Order order);
}