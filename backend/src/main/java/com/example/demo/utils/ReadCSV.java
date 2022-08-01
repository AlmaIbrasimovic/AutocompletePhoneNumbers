package com.example.demo.utils;
import com.example.demo.model.PhoneNumbers;
import org.springframework.web.multipart.MultipartFile;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import java.io.InputStream;

public class ReadCSV {
    public static String fileType = "text/csv";
    private String[] fileHeaders = {"Name", "Phone Number"};

    public static boolean isCorrectFormat(MultipartFile file) {
        return fileType.equals(file.getContentType());

    }

    public static List<PhoneNumbers> importCSV (InputStream file) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(file, "UTF-8"));
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {
            List<PhoneNumbers> phoneNumbers = new ArrayList<PhoneNumbers>();
            Iterable<CSVRecord> csvRecords = csvParser.getRecords();
            for (CSVRecord csvRecord : csvRecords) {
                PhoneNumbers phoneNumber = new PhoneNumbers(
                        csvRecord.get("Name"),
                        csvRecord.get("Phone Number")
                );
                phoneNumbers.add(phoneNumber);
            }
            return phoneNumbers;
        } catch (IOException e) {
            throw new RuntimeException("Failed to read CSV file: " + e.getMessage());
        }
    }
}
