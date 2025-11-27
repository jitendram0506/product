package com.org.sales.order.service;

import com.org.sales.order.model.Client;
import com.org.sales.order.model.IndividualClient;
import com.org.sales.order.model.ProfessionalClient;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class OnlinePriceService {
    private static final Map<String, Integer> INDIVIDUAL_PRICES = Map.of(
            "high_end_phone", 1500,
            "mid_range_phone", 800,
            "laptop", 1200
    );

    private static final Map<String, Integer> PROFESSIONAL_PRICES_HIGH_REVENUE = Map.of(
            "high_end_phone", 1000,
            "mid_range_phone", 550,
            "laptop", 900
    );

    private static final Map<String, Integer> PROFESSIONAL_PRICES_LOW_REVENUE = Map.of(
            "high_end_phone", 1150,
            "mid_range_phone", 600,
            "laptop", 1000
    );

    public double calculateTotal(Client client, Map<String, Integer> quantities) {
        Map<String, Integer> prices = getPrices(client);
        
        return quantities.entrySet().stream()
                .mapToDouble(data->{
                    String product=data.getKey();
                    int qty=data.getValue();
                    if(!prices.containsKey(product)){
                        throw new IllegalArgumentException("Un available product: " + product);
                    }
                    return prices.get(product) * qty;
                }).sum();
    }

    private Map<String, Integer> getPrices(Client client) {
        if (client instanceof IndividualClient) {
            return INDIVIDUAL_PRICES;
        }
        if (client instanceof ProfessionalClient pro) {
            return pro.getAnnualRevenue() > 10_000_000
                    ? PROFESSIONAL_PRICES_HIGH_REVENUE
                    : PROFESSIONAL_PRICES_LOW_REVENUE;
        }
        throw new IllegalArgumentException("Invalid client type");
    }
}
