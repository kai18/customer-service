package com.kaustubh.customerservice.repository;

import com.kaustubh.customerservice.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>, JpaSpecificationExecutor<Customer>,
        PagingAndSortingRepository<Customer, Long> {
    Customer findByPhoneNumber(String phoneNumber);
}
