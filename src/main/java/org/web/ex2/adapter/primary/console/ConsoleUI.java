package org.web.ex2.adapter.primary.console;

import org.web.ex2.domain.model.Order;
import org.web.ex2.domain.port.primary.OrderManagementUseCase;

import java.util.*;

public class ConsoleUI {

    private final OrderManagementUseCase service;
    private final Scanner scanner = new Scanner(System.in);

    public ConsoleUI(OrderManagementUseCase service) {
        this.service = service;
    }

    public void run() {
        while (true) {
            System.out.println("\n 1. Создать заказ \n 2. Отправить заказ\n 3. Подтвердить заказ\n 4. Получить заказ\n 5. Отклонить заказ\n 6. Показать все заказы\n 0. Выход");
            String input = scanner.nextLine();
            switch (input) {
                case "1" -> createOrder();
                case "2" -> updateStatus("send");
                case "3" -> updateStatus("confirm");
                case "4" -> updateStatus("receive");
                case "5" -> updateStatus("reject");
                case "6" -> showAllOrders();
                case "0" -> {
                    System.out.println("Выход");
                    return;
                }
            }
        }
    }

    private void createOrder() {
        List<String> names = new ArrayList<>();
        List<Integer> quantities = new ArrayList<>();
        while (true) {
            System.out.print("Продукт (или 'стоп'): ");
            String name = scanner.nextLine();
            if (name.equalsIgnoreCase("стоп")) break;
            System.out.print("Количество: ");
            int quantity = Integer.parseInt(scanner.nextLine());
            names.add(name);
            quantities.add(quantity);
        }
        Order order = service.createOrder(names, quantities);
        System.out.println("Создан заказ #" + order.getId());
    }

    private void updateStatus(String action) {
        System.out.print("ID заказа: ");
        String id = scanner.nextLine();
        switch (action) {
            case "send" -> service.sendOrder(id);
            case "confirm" -> service.confirmOrder(id);
            case "receive" -> service.receiveOrder(id);
            case "reject" -> service.rejectOrder(id);
        }
        System.out.println("Статус заказа обновлён.");
    }

    private void showAllOrders() {
        List<Order> orders = service.getAllOrders();
        for (Order o : orders) {
            System.out.printf("Номер заказа: #" + o.getId()+", Статус: "+ o.getStatus()+", Кол-во позиций: "+ o.getItems().size()+"\n");
        }
    }
}