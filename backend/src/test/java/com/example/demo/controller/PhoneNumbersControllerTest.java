package com.example.demo.controller;

import com.example.demo.DTO.PhoneNumbersContent;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@SpringBootTest
@AutoConfigureMockMvc
public class PhoneNumbersControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    void testReponseSizeAndStatus() {
        try {
            MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get("/api/v1/phone-numbers/autocomplete").param("query", "003929300921"))
                                  .andExpect(status().isOk()).andReturn();
            String json = mvcResult.getResponse().getContentAsString();
            ObjectMapper objectMapper = new ObjectMapper();
            PhoneNumbersContent content = new PhoneNumbersContent();
            content = objectMapper.readValue(json, new TypeReference<PhoneNumbersContent>(){});

            // Checking response content type
            assertEquals("application/json",
                        mvcResult.getResponse().getContentType());

            // Checking content size
            assertEquals(1, content.getContent().size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void testCheckName() {
        try {
            MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get("/api/v1/phone-numbers/autocomplete").param("query", "003929300921"))
                    .andExpect(status().isOk()).andReturn();
            String json = mvcResult.getResponse().getContentAsString();
            ObjectMapper objectMapper = new ObjectMapper();
            PhoneNumbersContent content = new PhoneNumbersContent();
            content = objectMapper.readValue(json, new TypeReference<PhoneNumbersContent>(){});

            // Checking response content type
            assertEquals("application/json",
                    mvcResult.getResponse().getContentType());

            // Checking content size
            assertEquals(1, content.getContent().size());

            // Checking name
            assertEquals("Blake Hopgood", content.getContent().get(0).getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void testCheckPhoneNumber() {
        try {
            MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get("/api/v1/phone-numbers/autocomplete").param("query", "003929300921"))
                    .andExpect(status().isOk()).andReturn();
            String json = mvcResult.getResponse().getContentAsString();
            ObjectMapper objectMapper = new ObjectMapper();
            PhoneNumbersContent content = new PhoneNumbersContent();
            content = objectMapper.readValue(json, new TypeReference<PhoneNumbersContent>(){});

            // Checking response content type
            assertEquals("application/json",
                    mvcResult.getResponse().getContentType());

            // Checking content size
            assertEquals(1, content.getContent().size());

            // Checking phone number
            assertEquals("003929300921", content.getContent().get(0).getPhoneNumber());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void testCheckEverything() {
        try {
            MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get("/api/v1/phone-numbers/autocomplete").param("query", "003929300921"))
                    .andExpect(status().isOk()).andReturn();
            String json = mvcResult.getResponse().getContentAsString();
            ObjectMapper objectMapper = new ObjectMapper();
            PhoneNumbersContent content = new PhoneNumbersContent();
            content = objectMapper.readValue(json, new TypeReference<PhoneNumbersContent>(){});

            // Checking response content type
            assertEquals("application/json", mvcResult.getResponse().getContentType());

            // Checking content size
            assertEquals(1, content.getContent().size());

            // Checking phone number
            assertEquals("003929300921", content.getContent().get(0).getPhoneNumber());

            // Checking name
            assertEquals("Blake Hopgood", content.getContent().get(0).getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void testCheckBigList() {
        try {
            MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get("/api/v1/phone-numbers/autocomplete").param("query", "00392"))
                    .andExpect(status().isOk()).andReturn();
            String json = mvcResult.getResponse().getContentAsString();
            ObjectMapper objectMapper = new ObjectMapper();
            PhoneNumbersContent content = new PhoneNumbersContent();
            content = objectMapper.readValue(json, new TypeReference<PhoneNumbersContent>(){});

            // Checking response content type
            assertEquals("application/json", mvcResult.getResponse().getContentType());

            // Checking phone number for third result
            assertEquals("003-9253 47", content.getContent().get(0).getPhoneNumber());

            // Checking name for first result
            assertEquals("Elmira Walshe", content.getContent().get(0).getName());

            // Checking name for second result
            assertEquals("Morris Cushwa", content.getContent().get(1).getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void testSearchHistoryByQuery() {
        try {
            mvc.perform(MockMvcRequestBuilders.get("/api/v1/phone-numbers/phone-numbers/autocomplete/history")
                    .param("query", "00392"))
                    .andExpect(status().isOk());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void testSearchHistoryByQueryAndSortByExecutionTime() {
        try {
            mvc.perform(MockMvcRequestBuilders.get("/api/v1/phone-numbers/phone-numbers/autocomplete/history")
                    .param("query", "00392")
                    .param("sort", "executionTime"))
                    .andExpect(status().isOk());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void incorrectSearchQuery() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/api/v1/phone-numbers/autocomplete")
                .param("query", "00392A"))
                .andExpect(content().string("Query format incorrect."))
                .andExpect(status().isBadRequest());
   }
}