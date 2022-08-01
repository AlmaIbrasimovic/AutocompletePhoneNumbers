package com.example.demo.DTO;

import java.util.List;

public class PhoneNumbersContent {
    private List<PhoneNumbersDTO> content;

    public PhoneNumbersContent(List<PhoneNumbersDTO> content) {
        this.content = content;
    }

    public PhoneNumbersContent() {
    }

    public List<PhoneNumbersDTO> getContent() {
        return content;
    }

    public void setContent(List<PhoneNumbersDTO> content) {
        this.content = content;
    }
}
