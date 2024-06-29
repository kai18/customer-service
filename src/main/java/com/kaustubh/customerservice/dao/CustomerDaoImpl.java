package com.kaustubh.customerservice.dao;

import com.kaustubh.customerservice.model.Customer;
import com.kaustubh.customerservice.repository.CustomerRepository;
import com.kaustubh.customerservice.util.CustomerSpecification;
import org.springframework.dao.TransientDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerDaoImpl implements CustomerDao {
    private final CustomerRepository customerRepository;

    public CustomerDaoImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    @Retryable(maxAttempts = 3, backoff = @Backoff(delay = 1000), retryFor = TransientDataAccessException.class)
    public Customer saveCustomer(Customer customer) {
        this.customerRepository.findByPhoneNumber(customer.getPhoneNumber());
        this.customerRepository.saveAll(List.of(customer));
        return customer;
    }

    @Override
    @Retryable(maxAttempts = 3, backoff = @Backoff(delay = 1000), retryFor = TransientDataAccessException.class)
    public Page<Customer> findAllCustomers(CustomerSpecification customerSpecification, Pageable pageable) {
        return this.customerRepository.findAll(customerSpecification, pageable);

    }

    @Override
    @Retryable(maxAttempts = 3, backoff = @Backoff(delay = 1000), retryFor = TransientDataAccessException.class)
    public Page<Customer> findAllCustomers(Pageable pageable) {
        return this.customerRepository.findAll(pageable);
    }

    @Override
    @Retryable(maxAttempts = 3, backoff = @Backoff(delay = 1000), retryFor = TransientDataAccessException.class)
    public void deleteCustomer(Customer customer) {
        this.customerRepository.delete(customer);
    }
}
