package com.kaustubh.customerservice.service;

import com.kaustubh.customerservice.model.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.requestreply.KafkaReplyTimeoutException;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CustomerPublishingServiceImpl implements CustomerPublishingService {
    private final KafkaTemplate<String, Customer> kafkaTemplate;
    private final String topic;

    public CustomerPublishingServiceImpl(KafkaTemplate<String, Customer> kafkaTemplate, @Value("${spring.kafka.producer.topic}") String topic) {
        this.kafkaTemplate = kafkaTemplate;
        this.topic = topic;
    }

    @Override
    @Retryable(retryFor = KafkaReplyTimeoutException.class, maxAttempts = 3, backoff = @Backoff(delay = 300))
    public void publishCustomer(Customer customer) {
        this.kafkaTemplate.send(topic, customer.getPhoneNumber(), customer);
    }
}
