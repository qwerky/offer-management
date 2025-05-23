package com.qwerky.offermanagement.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Pricing {

    private String currency;

    @JsonProperty("default")
    private Price defaultPrice;

    @JsonProperty("local")
    private Price[] localPrice;

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Price getDefaultPrice() {
        return defaultPrice;
    }

    public void setDefaultPrice(Price defaultPrice) {
        this.defaultPrice = defaultPrice;
    }

    public Price[] getLocal() {
        return localPrice;
    }

    public void setLocal(Price[] local) {
        this.localPrice = local;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Price {
        private String[] stores;
        private float[] wasPrice;
        private float currentPrice;
        private float unitPrice;

        private float[] exVatWasPrice;
        private float exVatCurrentPrice;
        private float exVatUnitPrice;

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

    }
}
