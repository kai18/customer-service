package com.kaustubh.customerservice.service;

import com.kaustubh.customerservice.dto.AddressRequest;
import com.kaustubh.customerservice.dto.CustomerRequest;
import com.kaustubh.customerservice.dto.CustomerResponse;
import com.kaustubh.customerservice.model.AddressType;
import com.kaustubh.customerservice.model.Customer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.requestreply.KafkaReplyTimeoutException;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class CustomerServiceImplTest {
    @MockBean
    private KafkaTemplate<String, Customer> kafkaTemplate;
    @Autowired
    private CustomerServiceImpl customerServiceImpl;

    @BeforeEach
    void setUp() {
    }

    @Test
    void testCreateCustomer() {
        when(kafkaTemplate.send(any(ProducerRecord.class))).thenThrow(new KafkaReplyTimeoutException("Did not get a reply"));
        CustomerResponse customerResponse = this.customerServiceImpl.createCustomer(CustomerRequest.builder()
                .phoneNumber("1234567890")
                .firstName("First")
                .lastName("Last")
                .age(20)
                .addresses(Collections.singletonList(AddressRequest.builder()
                        .city("Dehradun").type(AddressType.CURRENT).build()))
                .build());
        assertNotNull(customerResponse);
    }
}