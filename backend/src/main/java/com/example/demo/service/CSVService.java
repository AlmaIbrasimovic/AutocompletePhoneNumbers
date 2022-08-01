package com.example.demo.service;

import com.example.demo.model.PhoneNumbers;
import com.example.demo.repository.PhoneNumbersRepository;
import com.example.demo.utils.ReadCSV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class CSVService {

    @Autowired
    PhoneNumbersRepository phoneNumbersRepository;

    public void save(MultipartFile file) {
        try {
            List<PhoneNumbers> phoneNumbers = ReadCSV.importCSV(file.getInputStream());
            phoneNumbersRepository.saveAll(phoneNumbers);
        } catch (IOException e) {
            throw new RuntimeException("Failed to import CSV data into db: " + e.getMessage());
        }
    }
    public List<PhoneNumbers> getAllPhoneNumbers() {
        return phoneNumbersRepository.findAll();
    }
}
