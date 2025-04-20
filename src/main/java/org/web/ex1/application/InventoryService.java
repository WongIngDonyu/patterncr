package org.web.ex1.application;

import org.web.ex1.domain.IInventoryRepository;
import org.web.ex1.domain.InventoryEntry;
import org.web.ex1.domain.Product;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class InventoryService {
    private final IInventoryRepository repository;

    public InventoryService(IInventoryRepository repository) {
        this.repository = repository;
    }

    public void addProductEntry(String name, int quantity, LocalDate expiryDate) {
        Product product = new Product(name);
        InventoryEntry newEntry = new InventoryEntry(product, quantity, expiryDate);
        List<InventoryEntry> existingEntries = repository.findByProductName(name);
        boolean merged = false;
        for (InventoryEntry entry : existingEntries) {
            if (entry.getExpiryDate().equals(expiryDate)) {
                entry.setQuantity(entry.getQuantity() + quantity);
                merged = true;
                break;
            }
        }
        if (!merged) {
            repository.addEntry(newEntry);
        }
    }

    public void useProduct(String name, int quantity) {
        List<InventoryEntry> entries = repository.findByProductName(name);
        for (int i = 0; i < entries.size() - 1; i++) {
            for (int j = i + 1; j < entries.size(); j++) {
                if (entries.get(i).getExpiryDate().isAfter(entries.get(j).getExpiryDate())) {
                    InventoryEntry temp = entries.get(i);
                    entries.set(i, entries.get(j));
                    entries.set(j, temp);
                }
            }
        }
        for (InventoryEntry entry : new ArrayList<>(entries)) {
            if (quantity <= 0) break;
            int available = entry.getQuantity();
            int toUse = Math.min(quantity, available);
            entry.reduceQuantity(toUse);
            quantity -= toUse;
            if (entry.getQuantity() <= 0) {
                repository.removeEntry(entry);
            }
        }
    }

    public void writeOffExpired() {
        List<InventoryEntry> allEntries = repository.getAllEntries();
        for (InventoryEntry entry : new ArrayList<>(allEntries)) {
            if (entry.isExpired()) {
                repository.removeEntry(entry);
            }
        }
    }

    public void updateQuantity(String name, int newTotalQuantity) {
        List<InventoryEntry> entries = repository.findByProductName(name);
        if (!entries.isEmpty()) {
            entries.get(0).setQuantity(newTotalQuantity);
            for (int i = 1; i < entries.size(); i++) {
                repository.removeEntry(entries.get(i));
            }
        }
    }

    public List<InventoryEntry> getCriticalStock(int threshold) {
        List<InventoryEntry> result = new ArrayList<>();
        for (InventoryEntry entry : repository.getAllEntries()) {
            if (entry.isCriticalLevel(threshold)) {
                result.add(entry);
            }
        }
        return result;
    }

    public List<InventoryEntry> getInventoryReport() {
        return repository.getAllEntries();
    }
}