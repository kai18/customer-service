package com.kaustubh.customerservice.util;

import lombok.Getter;

@Getter
public enum SearchOperations {
    EQUALS(":");
    private final String operator;

    SearchOperations(String operator) {
        this.operator = operator;
    }
}
