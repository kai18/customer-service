package com.kaustubh.customerservice.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class CustomerResponse {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private int age;
    private BigDecimal spendingLimit;
    private List<AddressResponse> addresses;
}
