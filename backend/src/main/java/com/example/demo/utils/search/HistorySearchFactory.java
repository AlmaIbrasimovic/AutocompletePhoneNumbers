package com.example.demo.utils.search;

import com.example.demo.model.SearchHistory;
import com.example.demo.model.SearchRequest;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class HistorySearchFactory {
    public static TypedQuery createSearchQuery (SearchRequest searchRequest, EntityManager em) {
        HistorySearchCriteria historySearchCriteria = new HistorySearchCriteria(SearchHistory.class, em);

        if (searchRequest.getName() != null) {
            historySearchCriteria.withName(searchRequest.getName());
        }
        if (searchRequest.getQuery() != null) {
            historySearchCriteria.withQuery(searchRequest.getQuery());
        }
        if (searchRequest.getPhoneNumber() != null) {
            historySearchCriteria.withPhoneNumber(searchRequest.getPhoneNumber());
        }

        if (searchRequest.getDate() != null) {
            historySearchCriteria.withDate(searchRequest.getDate());
        }

        switch(searchRequest.getOrder()) {
            case "query":
                historySearchCriteria.withOrderQuery();
                break;
            case "executionTime":
                historySearchCriteria.withOrderExecutionTime();
                break;
            default:
                historySearchCriteria.withOrderDefault();
                break;
        }

        historySearchCriteria.withPageSize(searchRequest.getPageSize());
        historySearchCriteria.withPageNumber(searchRequest.getPageNumber());

        return historySearchCriteria.getSearchCriteria();
    }
}
