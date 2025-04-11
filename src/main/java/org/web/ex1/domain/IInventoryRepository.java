package org.web.ex1.domain;

public interface IInventoryRepository {
    Inventory loadInventory();
    void saveInventory(Inventory inventory);
}
