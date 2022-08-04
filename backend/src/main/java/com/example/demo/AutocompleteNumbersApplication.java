package com.example.demo;

import com.example.demo.service.CSVService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AutocompleteNumbersApplication {

    @Autowired
    CSVService csvService;

    public static void main(String[] args) {
        SpringApplication.run(AutocompleteNumbersApplication.class, args);
    }


    @Bean
    public CommandLineRunner databaseSeeder() {
        return (args) -> {
            csvService.save();
        };
    }
}
