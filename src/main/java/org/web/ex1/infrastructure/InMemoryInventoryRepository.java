package org.web.ex1.infrastructure;


import org.web.ex1.domain.IInventoryRepository;
import org.web.ex1.domain.Inventory;

public class InMemoryInventoryRepository implements IInventoryRepository {
    private Inventory inventory = new Inventory();

    @Override
    public Inventory loadInventory() {
        return inventory;
    }

    @Override
    public void saveInventory(Inventory inventory) {
        this.inventory = inventory;
    }
}