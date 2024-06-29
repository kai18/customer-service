package com.kaustubh.customerservice.dto;

import com.kaustubh.customerservice.model.AddressType;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddressRequest {
    @NotNull(message = "Address type is compulsory")
    private AddressType type;
    private String street;
    private String city;
    private String state;
    private String zip;
}
