package com.kaustubh.customerservice.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
public class CustomerRequest {
    @Size(min = 2, max = 100, message = "First Name must be between 2 and 100 characters")
    private String firstName;
    @Size(max = 100, message = "Last Name cannot exceed 100 characters")
    private String lastName;
    @Size(min = 10, max = 10, message = "Phone number must be 10 characters and contain only integers")
    private String phoneNumber;
    private int age;
    private BigDecimal spendingLimit;
    @Valid
    private List<AddressRequest> addresses;
}
