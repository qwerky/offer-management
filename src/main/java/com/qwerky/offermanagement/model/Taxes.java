package com.qwerky.offermanagement.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Taxes {

    @JsonProperty("default")
    private Tax[] defaultTaxes;
    private LocalTax[] localTaxes;

    public Tax[] getDefaultTaxes() {
        return defaultTaxes;
    }

    public void setDefaultTaxes(Tax[] defaultTaxes) {
        this.defaultTaxes = defaultTaxes;
    }

    public LocalTax[] getLocalTaxes() {
        return localTaxes;
    }

    public void setLocalTaxes(LocalTax[] localTaxes) {
        this.localTaxes = localTaxes;
    }
}

class LocalTax {
    private String[] stores;
    private Tax[] taxes;

    public String[] getStores() {
        return stores;
    }

    public void setStores(String[] stores) {
        this.stores = stores;
    }

    public Tax[] getTaxes() {
        return taxes;
    }

    public void setTaxes(Tax[] taxes) {
        this.taxes = taxes;
    }
}


class Tax {
    private String code;
    private float rate;
    private float amount;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }
}