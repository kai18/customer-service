package com.kaustubh.customerservice.dto;

import lombok.Data;

import java.util.List;

@Data
public class PagedResponse<T> {
    private List<T> items;
    private int pageNumber;
    private long totalItems;

    public PagedResponse(List<T> customerDtos, int pageNumber, long totalItems) {
        this.items = customerDtos;
        this.pageNumber = pageNumber;
        this.totalItems = totalItems;
    }
}
