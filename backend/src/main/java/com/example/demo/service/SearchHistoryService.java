package com.example.demo.service;

import com.example.demo.DTO.SearchHistoryDTO;
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

    /*public List<SearchHistory> getSearchHistory (Integer page, Integer pageSize, String sort) {
        Pageable paging;
        if (page >= 1) page -= 1;

        if (sort != null) {
            paging = PageRequest.of(page, pageSize, Sort.by(sort));
        }
        else {
            paging = PageRequest.of(page, pageSize);
        }

        Page<SearchHistory> pagedResult = searchHistoryRepository.findAll(paging);
        if (pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<SearchHistory>();
        }
    }*/

    public List<SearchHistory> getSearchHistory (SearchRequest searchRequest) {
        TypedQuery query = HistorySearchFactory.createSearchQuery(searchRequest, em);
        return query.getResultList();
    }
}
