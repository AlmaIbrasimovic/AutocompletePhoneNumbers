package com.example.demo.controller;

import com.example.demo.DTO.PhoneNumbersContent;
import com.example.demo.DTO.PhoneNumbersDTO;
import com.example.demo.DTO.SearchHistoryDTO;
import com.example.demo.model.SearchHistory;
import com.example.demo.model.SearchRequest;
import com.example.demo.service.PhoneNumbersService;
import com.example.demo.service.SearchHistoryService;
import com.example.demo.utils.Mapping;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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
        return new PhoneNumbersContent(phoneNumbersList);
    }

    @GetMapping("phone-numbers/autocomplete/history")
    @ApiOperation(value = "Retriving search history")
    public ResponseEntity<List<SearchHistoryDTO>> retrieveSearchHistory (@RequestParam(required = false) String name, @RequestParam(required = false) String phoneNumber, @RequestParam(required = false) String query, @RequestParam(required = false) LocalDate date, @RequestParam(required = false) Integer page, @RequestParam(required = false) Integer pageSize, @RequestParam(required = false) String sort) {
        if (sort == null) sort = "name";
        SearchRequest searchRequest = new SearchRequest(name, phoneNumber, query, date, page, pageSize, sort);
        List<SearchHistory> searchHistoryList = searchHistoryService.getSearchHistory(searchRequest);
        return new  ResponseEntity<>(Mapping.mapSearchHistoryListToDTO(searchHistoryList), HttpStatus.OK);
        //return new ResponseEntity<>(searchHistoryService.getSearchHistory(searchRequest), HttpStatus.OK);
    }
}
