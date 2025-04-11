package org.web.ex1.presentation;

import org.web.ex1.application.InventoryService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class InventoryConsoleUI {
    private final InventoryService service;
    private final Scanner scanner = new Scanner(System.in);

    public InventoryConsoleUI(InventoryService service) {
        this.service = service;
    }

    public void run() {
        while (true) {
            System.out.println("\n 1. Добавить продукт \n 2. Списать продукт \n 3. Удалить просроченные \n 4. Показать отчет \n 5. Критические запасы \n 0. Выход");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1 -> addProduct();
                case 2 -> writeOff();
                case 3 -> service.removeExpired();
                case 4 -> service.printReport();
                case 5 -> showCritical();
                case 0 -> {
                    System.out.println("Выход");
                    return;
                }
            }
        }
    }

    private void addProduct() {
        System.out.print("Название: ");
        String name = scanner.nextLine();
        System.out.print("Количество: ");
        int quantity = Integer.parseInt(scanner.nextLine());
        System.out.print("Срок годности (дата-месяц-год): ");
        String dateStr = scanner.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate date = LocalDate.parse(dateStr, formatter);
        service.addProduct(name, quantity, date);
    }

    private void writeOff() {
        System.out.print("Название: ");
        String name = scanner.nextLine();
        System.out.print("Количество: ");
        int quantity = Integer.parseInt(scanner.nextLine());
        service.writeOffProduct(name, quantity);
    }

    private void showCritical() {
        System.out.print("Введите порог критичного значения: ");
        int threshold = Integer.parseInt(scanner.nextLine());
        service.showCriticalProducts(threshold);
    }
}