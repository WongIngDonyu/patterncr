package org.web.ex2.domain.port.secondary;

import org.web.ex2.domain.model.Product;

public interface ProductRepository {
    Product findByName(String name);
}