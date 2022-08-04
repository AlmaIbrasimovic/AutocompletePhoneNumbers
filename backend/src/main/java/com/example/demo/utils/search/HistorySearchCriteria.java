package com.example.demo.utils.search;

import com.example.demo.model.SearchHistory;
import javax.persistence.EntityManager;
import javax.persistence.criteria.Predicate;

public class HistorySearchCriteria extends  SearchCriteriaBuilder<SearchHistory> {

    public HistorySearchCriteria(Class<SearchHistory> resourceClass, EntityManager entityManager) {
        super(resourceClass, entityManager);
    }

    public void withName(String name) {
        String searchQuery = "%name%:%" + name + "%";
        Predicate namePredicate = cb.like(resource.get("response"), searchQuery);
        predicates.add(namePredicate);
    }

    public void withPhoneNumber(String phoneNumber) {
        String searchQuery = "%phoneNumber%:%" + phoneNumber + "%";
        Predicate phoneNumberPredicate = cb.like(resource.get("response"), searchQuery);
        predicates.add(phoneNumberPredicate);
    }

    public void withQuery(String query) {
        Predicate queryPredicate = cb.like(resource.get("query"), query);
        predicates.add(queryPredicate);
    }

    public void withDate(String date) {
        Predicate datePredicate = cb.equal(resource.get("date"), date);
        predicates.add(datePredicate);
    }
}
