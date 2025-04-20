package org.web.ex1.domain;

import java.time.LocalDate;

public class InventoryEntry {
    private final Product product;
    private int quantity;
    private final LocalDate expiryDate;

    public InventoryEntry(Product product, int quantity, LocalDate expiryDate) {
        this.product = product;
        this.quantity = quantity;
        this.expiryDate = expiryDate;
    }

    public Product getProduct() {
        return product;
    }

    public String getProductName() {
        return product.getName();
    }

    public int getQuantity() {
        return quantity;
    }

    public void reduceQuantity(int amount) {
        this.quantity = Math.max(0, this.quantity - amount);
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public boolean isExpired() {
        return expiryDate.isBefore(LocalDate.now());
    }

    public boolean isCriticalLevel(int threshold) {
        return quantity <= threshold;
    }
}