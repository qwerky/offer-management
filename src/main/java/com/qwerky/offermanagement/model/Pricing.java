package com.qwerky.offermanagement.model;

public class Pricing {

    private String currency;
    private String[] stores;

    private float[] wasPrice;
    private float currentPrice;
    private float unitPrice;

    private float[] exVatWasPrice;
    private float exVatCurrentPrice;
    private float exVatUnitPrice;

    private Pricing[] local;

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String[] getStores() {
        return stores;
    }

    public void setStores(String[] stores) {
        this.stores = stores;
    }

    public float[] getWasPrice() {
        return wasPrice;
    }

    public void setWasPrice(float[] wasPrice) {
        this.wasPrice = wasPrice;
    }

    public float getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(float currentPrice) {
        this.currentPrice = currentPrice;
    }

    public float getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(float unitPrice) {
        this.unitPrice = unitPrice;
    }

    public float[] getExVatWasPrice() {
        return exVatWasPrice;
    }

    public void setExVatWasPrice(float[] exVatWasPrice) {
        this.exVatWasPrice = exVatWasPrice;
    }

    public float getExVatCurrentPrice() {
        return exVatCurrentPrice;
    }

    public void setExVatCurrentPrice(float exVatCurrentPrice) {
        this.exVatCurrentPrice = exVatCurrentPrice;
    }

    public float getExVatUnitPrice() {
        return exVatUnitPrice;
    }

    public void setExVatUnitPrice(float exVatUnitPrice) {
        this.exVatUnitPrice = exVatUnitPrice;
    }

    public Pricing[] getLocal() {
        return local;
    }

    public void setLocal(Pricing[] local) {
        this.local = local;
    }
}
