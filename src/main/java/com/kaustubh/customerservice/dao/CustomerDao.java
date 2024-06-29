package com.kaustubh.customerservice.dao;

import com.kaustubh.customerservice.model.Customer;
import com.kaustubh.customerservice.util.CustomerSpecification;
import org.springframework.dao.TransientDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;

public interface CustomerDao {
    @Retryable(maxAttempts = 3, backoff = @Backoff(delay = 1000), retryFor = TransientDataAccessException.class)
    Customer saveCustomer(Customer customer);

    @Retryable(maxAttempts = 3, backoff = @Backoff(delay = 1000), retryFor = TransientDataAccessException.class)
    Page<Customer> findAllCustomers(CustomerSpecification customerSpecification, Pageable pageable);

    @Retryable(maxAttempts = 3, backoff = @Backoff(delay = 1000), retryFor = TransientDataAccessException.class)
    Page<Customer> findAllCustomers(Pageable pageable);

    @Retryable(maxAttempts = 3, backoff = @Backoff(delay = 1000), retryFor = TransientDataAccessException.class)
    void deleteCustomer(Customer customer);
}
