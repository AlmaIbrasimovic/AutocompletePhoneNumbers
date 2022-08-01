package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.DTO.PhoneNumbersDTO;
import com.example.demo.model.PhoneNumbers;
import com.example.demo.repository.PhoneNumbersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class PhoneNumbersService {

    @Autowired
    private PhoneNumbersRepository phoneNumbersRepository;

    public List<PhoneNumbersDTO> autocompletePhoneNumber (String phoneNumberQuery) {
        List<PhoneNumbers> phoneNumbers = phoneNumbersRepository.findByPhoneNumber (phoneNumberQuery);
        List<PhoneNumbersDTO> result = new ArrayList<PhoneNumbersDTO>();
        for (PhoneNumbers phoneNumber:phoneNumbers) {
            PhoneNumbersDTO temp = new PhoneNumbersDTO (phoneNumber.getName(), phoneNumber.getPhoneNumber());
            result.add(temp);
        }

        return result;
    }
}
