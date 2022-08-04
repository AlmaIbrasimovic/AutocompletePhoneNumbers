package com.example.demo.utils.search;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

public class SearchCriteriaBuilder<T> implements ISearchCriteriaBuilder<T> {

    protected final EntityManager entityManager;
    protected final CriteriaBuilder cb;
    protected final CriteriaQuery<T> query;
    protected final Root<T> resource;
    protected List<Predicate> predicates;
    protected Order sort;
    protected Integer pageNumber;
    protected Integer pageSize;

    public SearchCriteriaBuilder(Class<T> resourceClass, EntityManager entityManager) {
        this.entityManager = entityManager;
        this.cb = entityManager.getCriteriaBuilder();
        this.query = this.cb.createQuery(resourceClass);
        this.resource = this.query.from(resourceClass);
        this.predicates = new ArrayList<>();
    }

    public void withOrderDefault() {
        this.sort = cb.asc(resource.get("query"));
    }

    public void withOrderQuery() {
        this.sort = cb.asc(resource.get("query"));
    }

    public void withOrderExecutionTime() {
        this.sort = cb.asc(resource.get("executionTime"));
    }

    public void withPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public void withPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public TypedQuery<T> getSearchCriteria() {
        this.query.select(resource)
                .where(predicates.toArray(new Predicate[predicates.size()]))
                .orderBy(sort);

        TypedQuery<T> typedQuery = entityManager.createQuery(this.query);
        typedQuery.setFirstResult((pageNumber-1)*pageSize);
        typedQuery.setMaxResults(pageSize);

        return typedQuery;
    }
}
