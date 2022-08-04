package com.example.demo.service;
import com.example.demo.model.SearchHistory;
import com.example.demo.model.SearchRequest;
import com.example.demo.repository.SearchHistoryRepository;
import com.example.demo.utils.search.HistorySearchFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Service
public class SearchHistoryService {

    @Autowired
    private SearchHistoryRepository searchHistoryRepository;

    @PersistenceContext
    private EntityManager em;

    public void saveSearchHistory (SearchHistory searchHistory) {
        searchHistoryRepository.save(searchHistory);
    }

    public List<SearchHistory> getSearchHistory (SearchRequest searchRequest) {
        if (searchRequest.getOrder() == null) searchRequest.setOrder("name");
        if (searchRequest.getPageNumber() == null) searchRequest.setPageNumber(1);
        if (searchRequest.getPageSize() == null) searchRequest.setPageSize(Integer.MAX_VALUE);

        TypedQuery query = HistorySearchFactory.createSearchQuery(searchRequest, em);

        return query.getResultList();
    }
}
