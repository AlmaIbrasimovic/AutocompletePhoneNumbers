package com.example.demo.controller;

import com.example.demo.DTO.PhoneNumbersContent;
import com.example.demo.DTO.PhoneNumbersDTO;
import com.example.demo.model.PhoneNumbers;
import com.example.demo.model.SearchHistory;
import com.example.demo.service.PhoneNumbersService;
import com.example.demo.service.SearchHistoryService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiOperation;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/api/v1/phone-numbers")
public class PhoneNumbersController {

    @Autowired
    PhoneNumbersService phoneNumbersService;

    @Autowired
    SearchHistoryService searchHistoryService;

    @GetMapping("/autocomplete")
    @ApiOperation(value = "Autocompleting phone number")
    PhoneNumbersContent phoneNumbersList(@RequestParam String query) throws Exception {
        long startTime = System.currentTimeMillis();
        ObjectMapper objectMapper = new ObjectMapper();
        String response= "";
        LocalDate currentDate = LocalDate.now();
        List<PhoneNumbersDTO> phoneNumbersList = phoneNumbersService.autocompletePhoneNumber(query);
        response += (objectMapper.writeValueAsString(phoneNumbersList));


        long endTime = System.currentTimeMillis();
        long executionTime = (endTime - startTime);

        SearchHistory searchHistory = new SearchHistory(query, response.toString(), currentDate.toString(), executionTime);
        searchHistoryService.saveSearchHistory(searchHistory);

        List<PhoneNumbersDTO> listCar = objectMapper.readValue(response, new TypeReference<List<PhoneNumbersDTO>>(){});
        for (PhoneNumbersDTO temp:listCar) {
            System.out.print(temp.getName() + ", " + temp.getPhoneNumber() + "\n");
        }
        return new PhoneNumbersContent(phoneNumbersList);
    }

    @GetMapping("phone-numbers/autocomplete/history")
    @ApiOperation(value = "Retriving search history")
    List<PhoneNumbers> retrieveSearchHistory (@RequestParam(required = false) String name, @RequestParam(required = false) String phoneNumber, @RequestParam(required = false) String query, @RequestParam(required = false) String date, @RequestParam(required = false) Integer page, @RequestParam(required = false) Integer pageSize, @RequestParam(required = false) String sort) {
        return phoneNumbersService.getAllPhoneNumbers(page, pageSize, sort);
    }

}
