package com.kaustubh.customerservice.service;

import com.kaustubh.customerservice.model.Customer;
import org.springframework.kafka.requestreply.KafkaReplyTimeoutException;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;

public interface CustomerPublishingService {
    @Retryable(retryFor = KafkaReplyTimeoutException.class, maxAttempts = 3, backoff = @Backoff(delay = 300))
    void publishCustomer(Customer customer);
}
