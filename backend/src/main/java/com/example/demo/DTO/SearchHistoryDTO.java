package com.example.demo.DTO;

import java.util.List;
public class SearchHistoryDTO {

    private String query;
    private String date;
    private long executionTime;
    private List<PhoneNumbersDTO> content;

    public SearchHistoryDTO(String query, String date, long executionTime, List<PhoneNumbersDTO> content) {
        this.query = query;
        this.date = date;
        this.executionTime = executionTime;
        this.content = content;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
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

    public List<PhoneNumbersDTO> getContent() {
        return content;
    }

    public void setContent(List<PhoneNumbersDTO> content) {
        this.content = content;
    }
}
