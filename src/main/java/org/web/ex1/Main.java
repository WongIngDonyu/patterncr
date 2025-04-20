package org.web.ex1;

import org.web.ex1.application.InventoryService;
import org.web.ex1.infrastructure.InMemoryInventoryRepository;
import org.web.ex1.presentation.InventoryConsoleUI;

public class Main {
    public static void main(String[] args) {
        var repo = new InMemoryInventoryRepository();
        var service = new InventoryService(repo);
        var ui = new InventoryConsoleUI(service);
        ui.start();
    }
}