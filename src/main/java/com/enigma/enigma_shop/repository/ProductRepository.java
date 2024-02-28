package com.enigma.enigma_shop.repository;

import com.enigma.enigma_shop.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {

    List<Product> findAllByNameLike(String name);
}
