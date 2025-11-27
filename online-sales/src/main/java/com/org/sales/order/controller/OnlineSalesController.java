package com.org.sales.order.controller;

import com.org.sales.order.dto.ShoppingCartRequest;
import com.org.sales.order.model.Client;
import com.org.sales.order.model.IndividualClient;
import com.org.sales.order.model.ProfessionalClient;
import com.org.sales.order.service.OnlinePriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cart")
public class OnlineSalesController {
    @Autowired
    private OnlinePriceService onlinePriceService;

    @PostMapping("/totals")
    public double cartTotal(@RequestBody ShoppingCartRequest request) {
        Client client;
        if ("individual".equalsIgnoreCase(request.clientType)) {
            client = new IndividualClient(request.clientId, request.firstName, request.lastName);

        } else if ("professional".equalsIgnoreCase(request.clientType)) {
            client = new ProfessionalClient(
                    request.clientId,
                    request.companyName,
                    request.vatNumber,
                    request.registrationNumber,
                    request.annualRevenue
            );

        } else {
            throw new IllegalArgumentException("In request the invalid client type");
        }
        return onlinePriceService.calculateTotal(client,request.quantities);
    }
}
