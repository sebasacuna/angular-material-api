package com.angular.api.forms.wsrest;

public class CollectRequest {

    private Long number;
    private String name;
    private Double weight;
    private String symbol;

    public CollectRequest(Long number, String name, Double weight, String symbol) {
        this.number = number;
        this.name = name;
        this.weight = weight;
        this.symbol = symbol;
    }

    public CollectRequest() {
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

}
