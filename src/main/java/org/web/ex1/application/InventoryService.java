package org.web.ex1.application;

import org.web.ex1.domain.IInventoryRepository;
import org.web.ex1.domain.Inventory;
import org.web.ex1.domain.InventoryReport;
import org.web.ex1.domain.Product;

import java.time.LocalDate;

public class InventoryService {
    private final IInventoryRepository repository;

    public InventoryService(IInventoryRepository repository) {
        this.repository = repository;
    }

    public void addProduct(String name, int quantity, LocalDate expiryDate) {
        Inventory inventory = repository.loadInventory();
        inventory.addProduct(new Product(name, quantity, expiryDate));
        repository.saveInventory(inventory);
    }

    public void writeOffProduct(String name, int quantity) {
        Inventory inventory = repository.loadInventory();
        inventory.writeOffProduct(name, quantity);
        repository.saveInventory(inventory);
    }

    public void removeExpired() {
        Inventory inventory = repository.loadInventory();
        inventory.removeExpiredProducts();
        repository.saveInventory(inventory);
    }

    public void printReport() {
        InventoryReport.generate(repository.loadInventory());
    }

    public void showCriticalProducts(int threshold) {
        Inventory inventory = repository.loadInventory();
        System.out.println("Продукты с критическим уровнем запаса:");
        for (Product p : inventory.getCriticalStock(threshold)) {
            System.out.printf(p.getName() +": "+ p.getQuantity()+ ", ");
        }
    }
}