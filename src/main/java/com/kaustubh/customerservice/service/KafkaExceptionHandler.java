package com.kaustubh.customerservice.service;

import com.kaustubh.customerservice.dao.CustomerDao;
import com.kaustubh.customerservice.model.Customer;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.kafka.support.ProducerListener;
import org.springframework.lang.Nullable;

@Slf4j
public class KafkaExceptionHandler implements ProducerListener<String, Customer> {
    private final CustomerDao customerDao;

    public KafkaExceptionHandler(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    @Override
    public void onSuccess(ProducerRecord<String, Customer> producerRecord, RecordMetadata recordMetadata) {
        log.info("Successfully processed record: {}", producerRecord);
    }

    @Override
    public void onError(ProducerRecord<String, Customer> producerRecord, @Nullable RecordMetadata recordMetadata,
                        Exception exception) {
        Customer customer = producerRecord.value();
        log.error("Error occurred when publishing Customer with id={}, phoneNumber={}",
                customer.getId(), customer.getPhoneNumber(), exception);

        customerDao.deleteCustomer(customer);

        log.info("Successfully reverted DB changes for customer id={}, phoneNumber={}",
                customer.getId(), customer.getPhoneNumber());

    }
}
