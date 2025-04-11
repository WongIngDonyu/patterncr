package org.web.ex1.domain;

import java.util.*;

public class Inventory {
    private final Map<String, Product> products = new HashMap<>();

    public void addProduct(Product product) {
        String name = product.getName();
        if (products.containsKey(name)) {
            Product existing = products.get(name);
            int newQuantity = existing.getQuantity() + product.getQuantity();
            existing.setQuantity(newQuantity);
            if (product.getExpiryDate().isAfter(existing.getExpiryDate())) {
                existing.setExpiryDate(product.getExpiryDate());
            }

        } else {
            products.put(name, product);
        }
    }

    public void writeOffProduct(String productName, int amount) {
        Product product = products.get(productName);
        if (product == null) {
            throw new IllegalArgumentException("Продукт " + productName + " не найден.");
        }
        int newQuantity = product.getQuantity() - amount;
        if (newQuantity <= 0) {
            products.remove(productName);
        } else {
            product.setQuantity(newQuantity);
        }
    }

    public void removeExpiredProducts() {
        products.values().removeIf(Product::isExpired);
    }

    public Collection<Product> getProducts() {
        return products.values();
    }

    public List<Product> getCriticalStock(int threshold) {
        List<Product> critical = new ArrayList<>();
        for (Product product : products.values()) {
            if (product.getQuantity() <= threshold) {
                critical.add(product);
            }
        }
        return critical;
    }
}