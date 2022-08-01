package com.example.demo.DTO;

import com.example.demo.model.PhoneNumbers;

public class PhoneNumbersDTO {
    private String name;
    private String phoneNumber;
    //private PhoneNumbers phoneNumbers;

    public PhoneNumbersDTO(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    /*public PhoneNumbersDTO (PhoneNumbers phoneNumbers) {
        this.name = phoneNumbers.getName();
        this.phoneNumber = phoneNumbers.getPhoneNumber();
    }*/

    public PhoneNumbersDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
