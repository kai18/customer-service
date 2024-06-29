package com.kaustubh.customerservice.util;

import org.springframework.stereotype.Component;


@Component
public class SearchCriteriaParser {
    public static CustomerSpecification getCustomerSearchCriteria(String filter) {
        String[] filters = filter.split(",");
        CustomerSpecification customerSpecification = null;
        for (String filterCriteria : filters) {
            String[] expressionParts = filterCriteria.split("[:]");
            SearchCriteria searchCriteria = new SearchCriteria(expressionParts[0],
                    filterCriteria.substring(expressionParts[0].length(), expressionParts[0].length() + 1), expressionParts[1]);
            if (customerSpecification == null) {
                customerSpecification = new CustomerSpecification(searchCriteria);
            } else {
                customerSpecification.and(new CustomerSpecification(searchCriteria));
            }
        }
        return customerSpecification;
    }
}
