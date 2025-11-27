package com.org.sales.order.model;


public abstract class Client {
    protected String clientId;
    public Client(String clientId){
        this.clientId=clientId;
    }
}
