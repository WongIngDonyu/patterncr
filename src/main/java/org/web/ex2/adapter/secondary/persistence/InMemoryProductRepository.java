package org.web.ex2.adapter.secondary.persistence;

import org.web.ex2.domain.model.Product;
import org.web.ex2.domain.port.secondary.ProductRepository;

import java.util.HashMap;
import java.util.Map;

public class InMemoryProductRepository implements ProductRepository {

    private final Map<String, Product> products = new HashMap<>();

    public InMemoryProductRepository() {
        products.put("Хлеб", new Product("Хлеб"));
        products.put("Мясо", new Product("Мясо"));
        products.put("Картошка", new Product("Картошка"));
        products.put("Рыба", new Product("Рыба"));
        products.put("Сало", new Product("Сало"));
    }

    @Override
    public Product findByName(String name) {
        Product product = products.get(name);
        if (product == null) {
            throw new IllegalArgumentException("Продукт не найден: " + name);
        }
        return product;
    }
}