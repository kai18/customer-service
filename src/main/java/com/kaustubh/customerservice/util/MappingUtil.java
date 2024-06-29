package com.kaustubh.customerservice.util;

import com.kaustubh.customerservice.dto.AddressRequest;
import com.kaustubh.customerservice.dto.AddressResponse;
import com.kaustubh.customerservice.dto.CustomerRequest;
import com.kaustubh.customerservice.dto.CustomerResponse;
import com.kaustubh.customerservice.model.Address;
import com.kaustubh.customerservice.model.Customer;

import java.util.ArrayList;

public class MappingUtil {
    private MappingUtil() {
    }

    public static Customer toCustomerDomain(CustomerRequest customerRequest) {
        Customer customer = new Customer();
        customer.setAddresses(customerRequest.getAddresses() != null ? customerRequest.getAddresses().parallelStream().map(address ->
                MappingUtil.toAddressDomain(customer.getId(), address)).toList() : new ArrayList<>());
        customer.setFirstName(customerRequest.getFirstName());
        customer.setLastName(customerRequest.getLastName());
        customer.setPhoneNumber(customerRequest.getPhoneNumber());
        customer.setSpendingLimit(customerRequest.getSpendingLimit());
        customer.setAge(customerRequest.getAge());
        return customer;
    }

    public static CustomerResponse toCustomerResponse(Customer customer) {
        CustomerResponse customerResponse = new CustomerResponse();
        customer.setId(customer.getId());
        customerResponse.setAddresses(customer.getAddresses().parallelStream().map(MappingUtil::toAddressResponse).toList());
        customerResponse.setFirstName(customer.getFirstName());
        customerResponse.setLastName(customer.getLastName());
        customerResponse.setSpendingLimit(customer.getSpendingLimit());
        customerResponse.setPhoneNumber(customer.getPhoneNumber());
        customerResponse.setAge(customer.getAge());
        return customerResponse;
    }

    public static Address toAddressDomain(Long customerId, AddressRequest addressRequest) {
        Address address = new Address();
        address.setCity(addressRequest.getCity());
        address.setState(addressRequest.getState());
        address.setZip(addressRequest.getZip());
        address.setCustomerId(address.getCustomerId() == null ? customerId : address.getCustomerId());
        address.setType(addressRequest.getType());
        address.setStreet(addressRequest.getStreet());
        return address;
    }

    public static AddressResponse toAddressResponse(Address address) {
        AddressResponse addressResponse = new AddressResponse();
        addressResponse.setCity(address.getCity());
        addressResponse.setState(address.getState());
        addressResponse.setZip(address.getZip());
        addressResponse.setType(address.getType());
        addressResponse.setStreet(address.getStreet());
        return addressResponse;
    }
}
