package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import com.example.demo.DTO.PhoneNumbersDTO;
import com.example.demo.model.PhoneNumbers;
import com.example.demo.repository.PhoneNumbersRepository;
import com.example.demo.utils.Mapping;
import com.example.demo.utils.Regex;
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

    public List<PhoneNumbersDTO> autocompletePhoneNumber (String phoneNumberQuery) throws Exception {
        phoneNumberQuery = phoneNumberQuery.replaceAll("[^A-Za-z0-9]","");
        Boolean containsLetters = Regex.containsLetter(phoneNumberQuery);
        if (containsLetters) {
            throw new Exception("Query format incorrect.");
        }
        List<PhoneNumbers> phoneNumbers = phoneNumbersRepository.findByPhoneNumber (phoneNumberQuery);
        return Mapping.mapPhoneNumberListToDTO(phoneNumbers);
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
