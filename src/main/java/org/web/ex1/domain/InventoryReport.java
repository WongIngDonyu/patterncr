package org.web.ex1.domain;

import java.time.format.DateTimeFormatter;

public class InventoryReport {
    public static void generate(Inventory inventory) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        System.out.println("Текущие запасы:");
        for (Product p : inventory.getProducts()) {
            String formattedDate = p.getExpiryDate().format(formatter);
            System.out.printf(p.getName() + ": " + p.getQuantity() + ", годен до " + formattedDate +"\n");
        }
    }
}