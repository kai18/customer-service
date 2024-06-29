package com.kaustubh.customerservice.model;

import jakarta.persistence.*;
import lombok.Data;

/**
 *
 */
@Data
@Entity
@Table(name = "ADDRESS", indexes = {@Index(columnList = "CITY")})
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "CUSTOMER_ID")
    private Long customerId;
    @Column(name = "TYPE", nullable = false)
    private AddressType type;
    @Column(name = "STREET")
    private String street;
    @Column(name = "CITY")
    private String city;
    @Column(name = "STATE")
    private String state;
    @Column(name = "ZIP")
    private String zip;
}
