package org.web.ex1.presentation;

import org.web.ex1.application.InventoryService;
import org.web.ex1.domain.InventoryEntry;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class InventoryConsoleUI {
    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private final InventoryService service;
    private final Scanner scanner = new Scanner(System.in);

    public InventoryConsoleUI(InventoryService service) {
        this.service = service;
    }

    public void start() {
        while (true) {
            System.out.println("\n 1. Добавить продукт\n 2. Использовать продукт\n 3. Списать просроченные\n 4. Инвентаризация (коррекция)\n 5. Критические запасы\n 6. Отчет\n 0. Выход ");
            switch (scanner.nextLine()) {
                case "1" -> addProduct();
                case "2" -> useProduct();
                case "3" -> {
                    service.writeOffExpired();
                    System.out.println("Просроченные продукты списаны.");
                }
                case "4" -> updateQuantity();
                case "5" -> showCritical();
                case "6" -> report();
                case "0" -> {
                    return;
                }
                default -> System.out.println("Неверный выбор");
            }
        }
    }

    private void addProduct() {
        System.out.print("Название: ");
        String name = scanner.nextLine();
        System.out.print("Количество: ");
        int quantity = Integer.parseInt(scanner.nextLine());
        System.out.print("Срок годности (дд-мм-гггг): ");
        String input = scanner.nextLine();
        LocalDate expiry = LocalDate.parse(input, dateFormatter);
        service.addProductEntry(name, quantity, expiry);
        System.out.println("Продукт добавлен.");
    }

    private void useProduct() {
        System.out.print("Название: ");
        String name = scanner.nextLine();
        System.out.print("Сколько использовать: ");
        int quantity = Integer.parseInt(scanner.nextLine());
        service.useProduct(name, quantity);
    }

    private void updateQuantity() {
        System.out.print("Название: ");
        String name = scanner.nextLine();
        System.out.print("Новое количество: ");
        int quantity = Integer.parseInt(scanner.nextLine());
        service.updateQuantity(name, quantity);
    }

    private void showCritical() {
        System.out.print("Порог критичности: ");
        int threshold = Integer.parseInt(scanner.nextLine());
        List<InventoryEntry> criticalEntries = service.getCriticalStock(threshold);
        if (criticalEntries.isEmpty()) {
            System.out.println("Нет продуктов с критическим уровнем.");
        } else {
            for (InventoryEntry entry : criticalEntries) {
                System.out.println(entry.getProductName() + " | Кол-во: " + entry.getQuantity() + " | Срок: " + entry.getExpiryDate().format(dateFormatter));
            }
        }
    }

    private void report() {
        List<InventoryEntry> reportEntries = service.getInventoryReport();
        if (reportEntries.isEmpty()) {
            System.out.println("Инвентарь пуст.");
        } else {
            System.out.println("Инвентарный отчет:");
            for (InventoryEntry entry : reportEntries) {
                System.out.println(entry.getProductName() + " | " + entry.getQuantity() + " шт. | Годен до: " + entry.getExpiryDate().format(dateFormatter));
            }
        }
    }
}