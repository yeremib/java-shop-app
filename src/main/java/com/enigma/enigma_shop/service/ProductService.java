package com.enigma.enigma_shop.service;

import com.enigma.enigma_shop.dto.request.NewProductRequest;
import com.enigma.enigma_shop.dto.request.SearchProductRequest;
import com.enigma.enigma_shop.entity.Product;
import org.springframework.data.domain.Page;


import java.util.List;

public interface ProductService {
    Product create(NewProductRequest request);
    Product getById(String id);
    Page<Product> getAll(SearchProductRequest request);
    List<Product> getAll(String name);
    Product update(Product product);
    void delete(String id);
}
