package org.web.ex1.domain;

import java.util.List;

public interface IInventoryRepository {
    void addEntry(InventoryEntry entry);
    List<InventoryEntry> getAllEntries();
    List<InventoryEntry> findByProductName(String name);
    void removeEntry(InventoryEntry entry);
}