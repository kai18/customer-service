package com.kaustubh.customerservice.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.List;

/**
 *
 */
@Data
@Entity
@Table(name = "CUSTOMER", uniqueConstraints = @UniqueConstraint(columnNames = {"PHONE_NUMBER"}),
        indexes = {@Index(columnList = "FIRST_NAME"), @Index(columnList = "LAST_NAME")})
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "FIRST_NAME", nullable = false)
    private String firstName;
    @Column(name = "LAST_NAME", nullable = false)
    private String lastName;
    @Column(name = "PHONE_NUMBER", nullable = false)
    @EqualsAndHashCode.Include
    private String phoneNumber;
    @Column(name = "AGE")
    private int age;
    @Column(name = "SPENDING_LIMIT")
    private BigDecimal spendingLimit;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "CUSTOMER_ID", referencedColumnName = "ID")
    private List<Address> addresses;
}
