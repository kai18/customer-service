package com.kaustubh.customerservice.controller;

import com.kaustubh.customerservice.dto.CustomerRequest;
import com.kaustubh.customerservice.dto.CustomerResponse;
import com.kaustubh.customerservice.dto.PagedResponse;
import com.kaustubh.customerservice.service.CustomerService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customer")
@Slf4j
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<CustomerResponse> createCustomer(@Valid @RequestBody CustomerRequest customerRequest) {
        log.info("Received request to create new customer: {}", customerRequest);
        CustomerResponse response = this.customerService.createCustomer(customerRequest);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping(value = "search", params = {"filter", "pageSize", "offset"})
    public ResponseEntity<PagedResponse<CustomerResponse>> searchCustomers(@RequestParam(value = "filter", required = false) String filter,
                                                                           @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                                                                           @RequestParam(value = "offset", defaultValue = "0") int offset) {
        log.info("Received request to search customers with filter: {}", filter);
        PagedResponse<CustomerResponse> response = this.customerService.findCustomers(filter, pageSize, offset);
        if (response == null || response.getItems().isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(response);
    }
}
