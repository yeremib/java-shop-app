package com.enigma.enigma_shop.service;

import com.enigma.enigma_shop.dto.request.SearchProductRequest;
import com.enigma.enigma_shop.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface ProductService {
    Product create(Product product);
    Product getById(String id);
    Page<Product> getAll(SearchProductRequest request);
    List<Product> getAll(String name);
    Product update(Product product);
    void delete(String id);
}
