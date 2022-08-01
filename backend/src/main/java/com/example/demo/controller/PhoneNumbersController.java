package com.example.demo.controller;

import com.example.demo.DTO.PhoneNumbersContent;
import com.example.demo.DTO.PhoneNumbersDTO;
import com.example.demo.model.PhoneNumbers;
import com.example.demo.service.PhoneNumbersService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/phone-numbers")
public class PhoneNumbersController {

    @Autowired
    PhoneNumbersService phoneNumbersService;

    @GetMapping("/autocomplete")
    @ApiOperation(value = "Autocompleting phone number")
    PhoneNumbersContent phoneNumbersList(@RequestParam String query) throws Exception {
        return new PhoneNumbersContent(phoneNumbersService.autocompletePhoneNumber(query));
    }
}
