package com.kaustubh.customerservice.config;

import com.kaustubh.customerservice.dao.CustomerDao;
import com.kaustubh.customerservice.service.KafkaExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.support.ProducerListener;

import javax.sql.DataSource;

@Configuration
@EnableKafka
public class AppConfig {
    @Bean("transactionManager")
    public DataSourceTransactionManager dataSourceTransactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean
    public ProducerListener kafkaProducerListener(CustomerDao customerDao) {
        return new KafkaExceptionHandler(customerDao);
    }
}
