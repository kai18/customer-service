package com.kaustubh.customerservice.util;

import com.kaustubh.customerservice.model.Customer;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CustomerSetOperationTest {
    @Test
    void testOnlyInListA() {
        Customer customer = new Customer();
        customer.setPhoneNumber("1234567890");
        Customer customer1 = new Customer();
        customer1.setPhoneNumber("1234567891");
        Customer customer2 = new Customer();
        customer2.setPhoneNumber("1234567892");

        Customer customer4 = new Customer();
        customer4.setPhoneNumber("1234567891");
        Customer customer5 = new Customer();
        customer5.setPhoneNumber("1234567893");
        Customer customer6 = new Customer();
        customer6.setPhoneNumber("1234567894");

        List<Customer> result = CustomerSetOperations.onlyInFirstList(Arrays.asList(customer, customer1, customer2),
                Arrays.asList(customer4, customer5, customer6));
        assertEquals(2, result.size());
        assertTrue(result.contains(customer) && result.contains(customer2));
    }

    @Test
    void testOnlyInListB() {
        Customer customer = new Customer();
        customer.setPhoneNumber("1234567890");
        Customer customer1 = new Customer();
        customer1.setPhoneNumber("1234567891");
        Customer customer2 = new Customer();
        customer2.setPhoneNumber("1234567892");

        Customer customer4 = new Customer();
        customer4.setPhoneNumber("1234567891");
        Customer customer5 = new Customer();
        customer5.setPhoneNumber("1234567893");
        Customer customer6 = new Customer();
        customer6.setPhoneNumber("1234567894");

        List<Customer> result = CustomerSetOperations.onlyInFirstList(Arrays.asList(customer4, customer5, customer6),
                Arrays.asList(customer, customer1, customer2));
        assertEquals(2, result.size());
        assertTrue(result.contains(customer5) && result.contains(customer6));
    }

    @Test
    void testAllDuplicates() {
        Customer customer = new Customer();
        customer.setPhoneNumber("1234567890");
        Customer customer1 = new Customer();
        customer1.setPhoneNumber("1234567891");
        Customer customer2 = new Customer();
        customer2.setPhoneNumber("1234567892");

        Customer customer4 = new Customer();
        customer4.setPhoneNumber("1234567891");
        Customer customer5 = new Customer();
        customer5.setPhoneNumber("1234567890");
        Customer customer6 = new Customer();
        customer6.setPhoneNumber("1234567892");

        List<Customer> result = CustomerSetOperations.onlyInFirstList(Arrays.asList(customer4, customer5, customer6),
                Arrays.asList(customer, customer1, customer2));
        assertEquals(0, result.size());
    }


    @Test
    void testIntersection() {
        Customer customer = new Customer();
        customer.setPhoneNumber("1234567890");
        Customer customer1 = new Customer();
        customer1.setPhoneNumber("1234567891");
        Customer customer2 = new Customer();
        customer2.setPhoneNumber("1234567892");

        Customer customer4 = new Customer();
        customer4.setPhoneNumber("1234567891");
        Customer customer5 = new Customer();
        customer5.setPhoneNumber("1234567893");
        Customer customer6 = new Customer();
        customer6.setPhoneNumber("1234567894");

        List<Customer> result = CustomerSetOperations.findIntersection(Arrays.asList(customer, customer1, customer2),
                Arrays.asList(customer4, customer5, customer6));
        assertEquals(1, result.size());
        assertTrue(result.contains(customer4));
    }

    @Test
    void testNoIntersection() {
        Customer customer = new Customer();
        customer.setPhoneNumber("1234567890");
        Customer customer1 = new Customer();
        customer1.setPhoneNumber("1234567891");
        Customer customer2 = new Customer();
        customer2.setPhoneNumber("1234567892");

        Customer customer4 = new Customer();
        customer4.setPhoneNumber("1234567895");
        Customer customer5 = new Customer();
        customer5.setPhoneNumber("1234567893");
        Customer customer6 = new Customer();
        customer6.setPhoneNumber("1234567894");

        List<Customer> result = CustomerSetOperations.findIntersection(Arrays.asList(customer, customer1, customer2),
                Arrays.asList(customer4, customer5, customer6));
        assertEquals(0, result.size());
    }

}
