package com.kaustubh.customerservice.service;

import com.kaustubh.customerservice.dto.CustomerRequest;
import com.kaustubh.customerservice.dto.CustomerResponse;
import com.kaustubh.customerservice.dto.PagedResponse;
import org.springframework.transaction.annotation.Transactional;

public interface CustomerService {
    @Transactional(transactionManager = "transactionManager")
    CustomerResponse createCustomer(CustomerRequest customerRequest);

    PagedResponse<CustomerResponse> findCustomers(String filter, int pageSize, int offset);
}
