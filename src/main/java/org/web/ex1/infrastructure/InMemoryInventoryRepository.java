package org.web.ex1.infrastructure;

import org.web.ex1.domain.IInventoryRepository;
import org.web.ex1.domain.InventoryEntry;

import java.util.ArrayList;
import java.util.List;

public class InMemoryInventoryRepository implements IInventoryRepository {
    private final List<InventoryEntry> entries = new ArrayList<>();

    @Override
    public void addEntry(InventoryEntry entry) {
        entries.add(entry);
    }

    @Override
    public List<InventoryEntry> getAllEntries() {
        return new ArrayList<>(entries);
    }

    @Override
    public List<InventoryEntry> findByProductName(String name) {
        List<InventoryEntry> result = new ArrayList<>();
        for (InventoryEntry entry : entries) {
            if (entry.getProductName().equalsIgnoreCase(name)) {
                result.add(entry);
            }
        }
        return result;
    }

    @Override
    public void removeEntry(InventoryEntry entry) {
        entries.remove(entry);
    }
}