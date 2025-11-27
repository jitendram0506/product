package com.org.sales.order.dto;

import lombok.Data;

import java.util.Map;

@Data
public class ShoppingCartRequest {
    //For Individual
    public String firstName;
    public String lastName;

    // For professional
    public String companyName;
    public String vatNumber;
    public String registrationNumber;
    public Double annualRevenue;

    //Common
    public String clientType;
    public String clientId;
    public Map<String, Integer> quantities;
}
