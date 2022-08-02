package com.example.demo.model;

import javax.persistence.*;

@Entity
@Table(name = "SearchHistory")
public class SearchHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String query;

    @Column(columnDefinition="TEXT")
    private String response;

    private String date;
    private long executionTime;

    public SearchHistory(String query, String response, String date, long executionTime) {
        this.query = query;
        this.response = response;
        this.date = date;
        this.executionTime = executionTime;
    }

    public SearchHistory() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getResponse() {
        return response;
    }


    public void setResponse(String response) {
        this.response = response;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public long getExecutionTime() {
        return executionTime;
    }

    public void setExecutionTime(long executionTime) {
        this.executionTime = executionTime;
    }
}
