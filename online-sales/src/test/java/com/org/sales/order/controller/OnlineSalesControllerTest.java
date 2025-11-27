package com.org.sales.order.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.org.sales.order.dto.ShoppingCartRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class OnlineSalesControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Test
    void testIndividualCart() throws Exception {
        ShoppingCartRequest req = new ShoppingCartRequest();
        req.clientType = "individual";
        req.clientId = "C100";
        req.firstName = "John";
        req.lastName = "Doe";
        req.quantities = Map.of("high_end_phone", 1, "mid_range_phone", 1, "laptop", 1);

        mockMvc.perform(post("/api/cart/totals")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(req)))
                .andExpect(status().isOk())
                .andExpect(content().string("3500.0"));
    }

    @Test
    void testProfessionalCartWhereAnnualRevenueMoreThan10Million() throws Exception {
        ShoppingCartRequest req = new ShoppingCartRequest();
        req.clientType = "professional";
        req.clientId = "C100";
        req.companyName = "ABCD Co. ltd";
        req.vatNumber = "ASD123";
        req.registrationNumber="AS123890";
        req.annualRevenue=100000000.0;
        req.quantities = Map.of("high_end_phone", 1, "mid_range_phone", 1, "laptop", 1);

        mockMvc.perform(post("/api/cart/totals")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(req)))
                .andExpect(status().isOk())
                .andExpect(content().string("2450.0"));
    }
    @Test
    void testProfessionalCartWhereAnnualRevenueLessThan10Million() throws Exception {
        ShoppingCartRequest req = new ShoppingCartRequest();
        req.clientType = "professional";
        req.clientId = "C100";
        req.companyName = "ABCD Co. ltd";
        req.vatNumber = "ASD123";
        req.registrationNumber="AS123890";
        req.annualRevenue=10000000.0;
        req.quantities = Map.of("high_end_phone", 1, "mid_range_phone", 1, "laptop", 1);

        mockMvc.perform(post("/api/cart/totals")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(req)))
                .andExpect(status().isOk())
                .andExpect(content().string("2750.0"));
    }
}
