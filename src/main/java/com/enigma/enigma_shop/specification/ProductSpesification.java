package com.enigma.enigma_shop.specification;

import com.enigma.enigma_shop.dto.request.SearchProductRequest;
import com.enigma.enigma_shop.entity.Product;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;


public class ProductSpesification {
    public static Specification<Product> getSpecification(SearchProductRequest request) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if(request.getName() != null) {
                Predicate namePredicate = criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("name")), "%" + request.getName() + "%"
                );
                predicates.add(namePredicate);
            }

            return query.where(predicates.toArray(new Predicate[]{})).getRestriction();
        };
    }
}
