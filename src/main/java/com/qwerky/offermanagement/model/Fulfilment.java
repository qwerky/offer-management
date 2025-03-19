package com.qwerky.offermanagement.model;

public class Fulfilment {

    private String name;
    private String shortMessage;
    private String longMessage;
    private String code;
    private Availability availability;
    private int minDeliveryDays;
    private int maxDeliveryDays;
    private float shippingPrice;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortMessage() {
        return shortMessage;
    }

    public void setShortMessage(String shortMessage) {
        this.shortMessage = shortMessage;
    }

    public String getLongMessage() {
        return longMessage;
    }

    public void setLongMessage(String longMessage) {
        this.longMessage = longMessage;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Availability getAvailability() {
        return availability;
    }

    public void setAvailability(Availability availability) {
        this.availability = availability;
    }

    public int getMinDeliveryDays() {
        return minDeliveryDays;
    }

    public void setMinDeliveryDays(int minDeliveryDays) {
        this.minDeliveryDays = minDeliveryDays;
    }

    public int getMaxDeliveryDays() {
        return maxDeliveryDays;
    }

    public void setMaxDeliveryDays(int maxDeliveryDays) {
        this.maxDeliveryDays = maxDeliveryDays;
    }

    public float getShippingPrice() {
        return shippingPrice;
    }

    public void setShippingPrice(float shippingPrice) {
        this.shippingPrice = shippingPrice;
    }
}

class Availability {

    private String zone;
    private String[] stores;

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public String[] getStores() {
        return stores;
    }

    public void setStores(String[] stores) {
        this.stores = stores;
    }
}
