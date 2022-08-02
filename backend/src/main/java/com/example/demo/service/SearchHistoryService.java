package com.example.demo.service;

import com.example.demo.model.PhoneNumbers;
import com.example.demo.model.SearchHistory;
import com.example.demo.repository.PhoneNumbersRepository;
import com.example.demo.repository.SearchHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class SearchHistoryService {

    @Autowired
    private SearchHistoryRepository searchHistoryRepository;

    public void saveSearchHistory (SearchHistory searchHistory) {
        searchHistoryRepository.save(searchHistory);
    }

    public List<SearchHistory> getSearchHistory (Integer page, Integer pageSize, String sort) {
        Pageable paging;
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
    }
}
