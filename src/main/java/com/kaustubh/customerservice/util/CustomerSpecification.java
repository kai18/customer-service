package com.kaustubh.customerservice.util;

import com.kaustubh.customerservice.exception.InvalidSearchCriteriaException;
import com.kaustubh.customerservice.model.Address;
import com.kaustubh.customerservice.model.Customer;
import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;

public class CustomerSpecification implements Specification<Customer> {
    private final SearchCriteria searchCriteria;

    public CustomerSpecification(SearchCriteria searchCriteria) {
        this.searchCriteria = searchCriteria;
    }

    @Override
    public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        Join<Customer, Address> addressJoin = root.join("addresses", JoinType.LEFT);
        if (SearchOperations.EQUALS.getOperator().equals(searchCriteria.getOperator())) {
            if (searchCriteria.getAttribute().contains("city"))
                return criteriaBuilder.equal(criteriaBuilder.lower(addressJoin.get("city")), searchCriteria.getValue().toLowerCase());
            else {
                return criteriaBuilder.equal(criteriaBuilder.lower(root.get(searchCriteria.getAttribute())), searchCriteria.getValue().toLowerCase());
            }
        }
        throw new InvalidSearchCriteriaException(searchCriteria.toString());
    }
}
