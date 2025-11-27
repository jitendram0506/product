package com.org.sales.order.model;

public class ProfessionalClient extends Client{
    private String companyName;
    private String vatNumber;
    private String registrationNumber;
    private double annualRevenue;
    public ProfessionalClient(String clientId, String companyName, String vatNumber,
                              String registrationNumber, double annualRevenue) {
        super(clientId);
        this.companyName = companyName;
        this.vatNumber = vatNumber;
        this.registrationNumber = registrationNumber;
        this.annualRevenue = annualRevenue;
    }
    public double getAnnualRevenue() {
        return annualRevenue;
    }
}
