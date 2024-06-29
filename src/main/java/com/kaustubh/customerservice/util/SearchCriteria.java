package com.kaustubh.customerservice.util;

import lombok.Data;

@Data
public class SearchCriteria {
    private String operator;
    private String value;
    private String attribute;

    public SearchCriteria(String attribute, String operator, String value) {
        this.attribute = attribute;
        this.operator = operator;
        this.value = value;
    }
}
