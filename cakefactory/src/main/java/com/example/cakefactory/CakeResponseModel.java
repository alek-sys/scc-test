package com.example.cakefactory;

public class CakeResponseModel {

    public CakeResponseModel() {
    }

    CakeResponseModel(String name, Long qty) {
        this.name = name;
        this.qty = qty;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getQty() {
        return qty;
    }

    public void setQty(Long qty) {
        this.qty = qty;
    }

    private String name;
    private Long qty;
}
