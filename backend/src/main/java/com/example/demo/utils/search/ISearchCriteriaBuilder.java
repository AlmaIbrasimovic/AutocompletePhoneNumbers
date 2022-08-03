package com.example.demo.utils.search;
import javax.persistence.TypedQuery;

public interface ISearchCriteriaBuilder<T> {
    void withOrderDefault();
    void withOrderPhoneNumber();
    void withOrderQuery();
    void withOrderExecutionTime();
    void withPageNumber(Integer pageNumber);
    void withPageSize(Integer pageSize);
    TypedQuery<T> getSearchCriteria();
}
