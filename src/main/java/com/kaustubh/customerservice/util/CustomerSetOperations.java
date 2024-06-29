package com.kaustubh.customerservice.util;

import com.kaustubh.customerservice.model.Customer;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CustomerSetOperations {
    public static List<Customer> onlyInFirstList(List<Customer> customerA, List<Customer> customerB) {
        Set<Customer> customerBSet = new HashSet<>(customerB);
        return customerA.stream().filter(customer -> !customerBSet.contains(customer)).toList();
    }

    public static List<Customer> findIntersection(List<Customer> customerA, List<Customer> customerB) {
        Set<Customer> customerBSet = new HashSet<>(customerB);
        return customerA.stream().filter(customerBSet::contains).toList();
    }
}
