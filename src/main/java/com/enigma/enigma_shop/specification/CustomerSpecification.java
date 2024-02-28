package com.enigma.enigma_shop.specification;

import com.enigma.enigma_shop.dto.request.SearchCustomerRequest;
import com.enigma.enigma_shop.entity.Customer;
import com.enigma.enigma_shop.util.DateUtil;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CustomerSpecification {

    public static Specification<Customer> getSpecification(SearchCustomerRequest request) {
        return (root, cq, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (request.getName() != null) {
                Predicate namePredicate = cb.like(cb.lower(root.get("name")), "%" + request.getName().toLowerCase() + "%");
                predicates.add(namePredicate);
            }

            if(request.getMobilePhoneNumber() != null) {
                Predicate phonePredicate = cb.equal(root.get("mobilePhoneNo"), request.getMobilePhoneNumber());
                predicates.add(phonePredicate);
            }

            if (request.getBirthDate() != null) {
                Date tempDate = DateUtil.parseDate(request.getBirthDate(), "yyyy-MM-dd");
                Predicate birthDatePredicate = cb.equal(root.get("birthDate"), tempDate);
                predicates.add(birthDatePredicate);
            }

            if (request.getStatus() != null) {
                Predicate statusPredicate = cb.equal(root.get("status"), request.getStatus());
                predicates.add(statusPredicate);
            }

            return cq.where(predicates.toArray(new Predicate[]{})).getRestriction();

        };
    }
}
