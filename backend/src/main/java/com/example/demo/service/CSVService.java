package com.example.demo.service;

import com.example.demo.model.PhoneNumbers;
import com.example.demo.repository.PhoneNumbersRepository;
import com.example.demo.utils.ReadCSV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

@Service
public class CSVService {

    @Autowired
    PhoneNumbersRepository phoneNumbersRepository;

    public void save() {
        System.out.print(phoneNumbersRepository.findAll().size() );
        if (phoneNumbersRepository.findAll().size() == 0) {
            try {
                File resource = new ClassPathResource("phone_numbers_65535.csv").getFile();
                MultipartFile multipartFile = new MockMultipartFile("phone_numbers_65535.csv", new FileInputStream(resource));
                List<PhoneNumbers> phoneNumbers = ReadCSV.importCSV(multipartFile.getInputStream());
                phoneNumbersRepository.saveAll(phoneNumbers);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public List<PhoneNumbers> getAllPhoneNumbers() {
        return phoneNumbersRepository.findAll();
    }
}
