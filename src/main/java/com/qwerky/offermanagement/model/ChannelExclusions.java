package com.qwerky.offermanagement.model;

public class ChannelExclusions {

    private String[] visible;
    private String[] sellable;
    private String[] discoverable;

    public String[] getVisible() {
        return visible;
    }

    public void setVisible(String[] visible) {
        this.visible = visible;
    }

    public String[] getSellable() {
        return sellable;
    }

    public void setSellable(String[] sellable) {
        this.sellable = sellable;
    }

    public String[] getDiscoverable() {
        return discoverable;
    }

    public void setDiscoverable(String[] discoverable) {
        this.discoverable = discoverable;
    }
}
