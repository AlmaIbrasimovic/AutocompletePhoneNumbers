package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.DTO.PhoneNumbersDTO;
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

@Service
public class PhoneNumbersService {

    private final PhoneNumbersRepository phoneNumbersRepository;

    @Autowired
    public PhoneNumbersService(PhoneNumbersRepository phoneNumbersRepository) {
        this.phoneNumbersRepository = phoneNumbersRepository;
    }

    public List<PhoneNumbersDTO> autocompletePhoneNumber (String phoneNumberQuery) {
        List<PhoneNumbers> phoneNumbers = phoneNumbersRepository.findByPhoneNumber (phoneNumberQuery);
        List<PhoneNumbersDTO> result = new ArrayList<PhoneNumbersDTO>();
        for (PhoneNumbers phoneNumber:phoneNumbers) {
            PhoneNumbersDTO temp = new PhoneNumbersDTO (phoneNumber.getName(), phoneNumber.getPhoneNumber());
            result.add(temp);
        }

        return result;
    }

    public void saveSearchHistory (SearchHistory searchHistory) {

    }
    public List<PhoneNumbers> getAllPhoneNumbers (Integer page, Integer pageSize, String sort) {
        Pageable paging;
        if (sort != null) {
            paging = PageRequest.of(page, pageSize, Sort.by(sort));
        }
        else {
            paging = PageRequest.of(page, pageSize);
        }
        Page<PhoneNumbers> pagedResult = phoneNumbersRepository.findAll(paging);
        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<PhoneNumbers>();
        }
    }
}
