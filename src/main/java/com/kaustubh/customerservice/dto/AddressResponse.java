package com.kaustubh.customerservice.dto;

import com.kaustubh.customerservice.model.AddressType;
import lombok.Data;

@Data
public class AddressResponse {
    private AddressType type;
    private String street;
    private String city;
    private String state;
    private String zip;
}
