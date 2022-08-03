package com.example.demo.model;

import java.time.LocalDate;

public class SearchRequest {
    private String name;
    private String phoneNumber;
    private String query;
    private LocalDate date;
    private Integer pageNumber;
    private Integer pageSize;
    private String order;

    public SearchRequest(String name, String phoneNumber, String query, LocalDate date, Integer pageNumber, Integer pageSize, String order) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.query = query;
        this.date = date;
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.order = order;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }
}
