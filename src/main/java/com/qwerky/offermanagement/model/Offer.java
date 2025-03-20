package com.qwerky.offermanagement.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.springframework.data.annotation.Id;

/**
 * Represents an offer using the Standard Offer Data model.
 */
public class Offer {

    private String id;
    private boolean valid;
    private String type;
    private ChannelExclusions channelExclusions;
    private String tennant;
    private Seller seller;
    private GSPR gspr;
    private Product product;
    private Pricing pricing;
    private Taxes taxes;
    private Promotion[] promotions;
    private Fulfilment[] fulfilmentOptions;

    @Id
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ChannelExclusions getChannelExclusions() {
        return channelExclusions;
    }

    public void setChannelExclusions(ChannelExclusions channelExclusions) {
        this.channelExclusions = channelExclusions;
    }

    public String getTennant() {
        return tennant;
    }

    public void setTennant(String tennant) {
        this.tennant = tennant;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public GSPR getGspr() {
        return gspr;
    }

    public void setGspr(GSPR gspr) {
        this.gspr = gspr;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Pricing getPricing() {
        return pricing;
    }

    public void setPricing(Pricing pricing) {
        this.pricing = pricing;
    }

    public Taxes getTaxes() {
        return taxes;
    }

    public void setTaxes(Taxes taxes) {
        this.taxes = taxes;
    }

    public Promotion[] getPromotions() {
        return promotions;
    }

    public void setPromotions(Promotion[] promotions) {
        this.promotions = promotions;
    }

    public Fulfilment[] getFulfilmentOptions() {
        return fulfilmentOptions;
    }

    public void setFulfilmentOptions(Fulfilment[] fulfilmentOptions) {
        this.fulfilmentOptions = fulfilmentOptions;
    }

    @Override
    public boolean equals(Object other) {
        return EqualsBuilder.reflectionEquals(this, other, true);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(id).append(valid).append(type).append(channelExclusions).append(tennant).append(seller).append(gspr).append(product).append(pricing).append(taxes).append(promotions).append(fulfilmentOptions).toHashCode();
    }
}
