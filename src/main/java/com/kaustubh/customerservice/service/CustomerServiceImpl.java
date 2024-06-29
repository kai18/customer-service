package com.kaustubh.customerservice.service;

import com.kaustubh.customerservice.dao.CustomerDao;
import com.kaustubh.customerservice.dao.CustomerDaoImpl;
import com.kaustubh.customerservice.dto.CustomerRequest;
import com.kaustubh.customerservice.dto.CustomerResponse;
import com.kaustubh.customerservice.dto.PagedResponse;
import com.kaustubh.customerservice.model.Customer;
import com.kaustubh.customerservice.util.CustomerSpecification;
import com.kaustubh.customerservice.util.MappingUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static com.kaustubh.customerservice.util.SearchCriteriaParser.getCustomerSearchCriteria;

@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {
    private final CustomerPublishingService customerPublishingService;
    private final CustomerDao customerDaoImpl;

    public CustomerServiceImpl(CustomerPublishingService customerPublishingService, CustomerDaoImpl customerDaoImpl) {
        this.customerPublishingService = customerPublishingService;
        this.customerDaoImpl = customerDaoImpl;
    }

    /**
     * Saves to DB and publishes cusomer pojo to kafka.
     * Transactional will revert db changes if an error occurs in Kafka
     *
     * @param customerRequest
     * @return
     */
    @Override
    @Transactional(transactionManager = "transactionManager")
    public CustomerResponse createCustomer(CustomerRequest customerRequest) {
        Customer customer = MappingUtil.toCustomerDomain(customerRequest);
        log.info("Saving Customer to DB:{}", customer);
        this.customerDaoImpl.saveCustomer(customer);
        log.info("Saved new Customer to DB with id={}", customer.getId());
        log.info("Publishing Customer id={} phoneNumber={} to Kafka", customer.getId(), customer.getPhoneNumber());
        this.customerPublishingService.publishCustomer(customer);
        return MappingUtil.toCustomerResponse(customer);
    }

    /**
     * Parses filter expression, and creates a criteria to be used in the where clause by JPA
     *
     * @param filter
     * @return
     */
    @Override
    public PagedResponse<CustomerResponse> findCustomers(String filter, int pageSize, int offset) {
        List<Customer> customers = new ArrayList<>();
        int pageNumber = offset / pageSize;
        long totalItems = 0;
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        if (filter == null || filter.isEmpty()) {
            Page<Customer> items = this.customerDaoImpl.findAllCustomers(pageable);
            totalItems = items.getTotalElements();
            customers.addAll(items.getContent());
        } else {
            CustomerSpecification customerSpecification = getCustomerSearchCriteria(filter);
            Page<Customer> items = this.customerDaoImpl.findAllCustomers(customerSpecification, pageable);
            totalItems = items.getTotalElements();
            customers.addAll(items.getContent());
        }
        List<CustomerResponse> customerDtos = customers.stream().map(MappingUtil::toCustomerResponse).toList();
        log.info("Total Customers found: {}", totalItems);
        return new PagedResponse<>(customerDtos, pageNumber, totalItems);
    }

}
