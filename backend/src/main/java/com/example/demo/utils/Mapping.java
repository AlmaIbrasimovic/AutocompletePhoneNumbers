package com.example.demo.utils;

import com.example.demo.DTO.PhoneNumbersDTO;
import com.example.demo.DTO.SearchHistoryDTO;
import com.example.demo.model.PhoneNumbers;
import com.example.demo.model.SearchHistory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Mapping {

    public static List<PhoneNumbersDTO> mapPhoneNumberListToDTO(List<PhoneNumbers> phoneNumbers) {
        return phoneNumbers.stream().map(
                phoneNumber -> {return mapPhoneNumberToPhoneNumberDTO(phoneNumber);
                }
        ).collect(Collectors.toList());
    }

    public static List<SearchHistoryDTO> mapSearchHistoryListToDTO(List<SearchHistory> searchHistoryList) {
        return searchHistoryList.stream().map(
                searchHistory -> {return mapSearcHistoryToSearchHistoryDTO(searchHistory);
                }
        ).collect(Collectors.toList());
    }

    public static PhoneNumbersDTO mapPhoneNumberToPhoneNumberDTO (PhoneNumbers phoneNumber) {
        return new PhoneNumbersDTO(phoneNumber.getName(), phoneNumber.getPhoneNumber());
    }

    public static SearchHistoryDTO mapSearcHistoryToSearchHistoryDTO (SearchHistory searchHistory) {
        ObjectMapper objectMapper = new ObjectMapper();
        List<PhoneNumbersDTO> phoneNumbers = new ArrayList<>();

        try {
            phoneNumbers = objectMapper.readValue(searchHistory.getResponse(), new TypeReference<List<PhoneNumbersDTO>>(){});
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return new SearchHistoryDTO(searchHistory.getQuery(), searchHistory.getDate(), searchHistory.getExecutionTime(), phoneNumbers);
    }
}
