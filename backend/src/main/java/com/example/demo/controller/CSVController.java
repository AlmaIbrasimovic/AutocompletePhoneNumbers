package com.example.demo.controller;

import com.example.demo.service.CSVService;
import com.example.demo.utils.ReadCSV;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/csv")
public class CSVController {

    @Autowired
    CSVService csvService;

    @PostMapping ("/upload")
    @ApiOperation(value = "Uploading CSV file to DB!")
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<Object> uploadCSVFileToDB (@RequestParam("csvFile") MultipartFile csvFile) {
        String message = "";
        if (ReadCSV.isCorrectFormat(csvFile)) {
            try {
                csvService.save(csvFile);
                message = "CSV file uploaded successfully: " + csvFile.getOriginalFilename();
                return new ResponseEntity<> (csvFile, HttpStatus.OK);
            } catch (Exception e) {
                message = "Could not upload the CSV file: " + csvFile.getOriginalFilename() + "!";
                return new ResponseEntity<> (csvFile, HttpStatus.BAD_REQUEST);
            }
        }
        message = "Please upload a csv file!";
        return new ResponseEntity<> (csvFile, HttpStatus.BAD_REQUEST);
    }
}
