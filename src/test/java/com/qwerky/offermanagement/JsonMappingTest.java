package com.qwerky.offermanagement;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qwerky.offermanagement.model.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class JsonMappingTest {

    @Test
    public void serialise() throws IOException {
        Offer offer = new Offer();
        offer.setId("1P-54321-BQUK");
        offer.setValid(true);
        offer.setType("1P");
        offer.setTennant("BQUK");

        ChannelExclusions exclusions = new ChannelExclusions();
        exclusions.setDiscoverable(new String[]{"123"});
        exclusions.setSellable(new String[]{"234"});
        exclusions.setVisible(new String[]{"345"});
        offer.setChannelExclusions(exclusions);

        Seller seller = new Seller();
        seller.setName("B & Q");
        seller.setId("BQUK");
        offer.setSeller(seller);

        GSPR gspr = new GSPR();
        gspr.setManufacturer("ACME corp");
        gspr.setCity("Anytown");
        gspr.setCountry("United Kingdom");
        gspr.setEmail("anyone@example.com");
        gspr.setPostcode("AA1 1AA");
        gspr.setAddressLine1("Unit 4a");
        gspr.setAddressLine2("10 Any Street");
        gspr.setEuRepresentative("someone@example.com");
        offer.setGspr(gspr);

        Product product = new Product();
        product.setEan("54321");
        offer.setProduct(product);

        Pricing pricing = new Pricing();
        pricing.setCurrency("GBP");
        Pricing.Price defaultPrice = new Pricing.Price();
        defaultPrice.setWasPrice(new float[]{15.00f});
        defaultPrice.setCurrentPrice(12.00f);
        defaultPrice.setUnitPrice(6.00f);
        defaultPrice.setExVatWasPrice(new float[]{12.50f});
        defaultPrice.setExVatCurrentPrice(10.00f);
        defaultPrice.setExVatUnitPrice(5.00f);
        pricing.setDefaultPrice(defaultPrice);

        Pricing.Price localPrice = new Pricing.Price();
        localPrice.setStores(new String[]{"123", "456", "789"});
        localPrice.setWasPrice(new float[]{18.00f});
        localPrice.setCurrentPrice(15.00f);
        localPrice.setUnitPrice(7.50f);
        localPrice.setExVatWasPrice(new float[]{15.00f});
        localPrice.setExVatCurrentPrice(12.00f);
        localPrice.setExVatUnitPrice(6.00f);
        pricing.setLocal(new Pricing.Price[]{localPrice});

        offer.setPricing(pricing);

        Taxes taxes = new Taxes();
        Taxes.Tax vat1 = new Taxes.Tax();
        vat1.setCode("VAT");
        vat1.setRate(0.2f);
        vat1.setAmount(2.00f);
        taxes.setDefaultTaxes(new Taxes.Tax[]{vat1});

        Taxes.LocalTax localTax1 = new Taxes.LocalTax();
        localTax1.setStores(new String[]{"123", "456"});
        Taxes.Tax eco = new Taxes.Tax();
        eco.setCode("ECO");
        eco.setRate(0.05f);
        eco.setAmount(0.5f);
        localTax1.setTaxes(new Taxes.Tax[]{eco, vat1});
        Taxes.LocalTax localTax2 = new Taxes.LocalTax();
        localTax2.setStores(new String[]{"789"});
        Taxes.Tax vat2 = new Taxes.Tax();
        vat2.setCode("VAT");
        vat2.setRate(0.1f);
        vat2.setAmount(1.00f);
        localTax2.setTaxes(new Taxes.Tax[]{vat2});
        taxes.setLocalTaxes(new Taxes.LocalTax[]{localTax1, localTax2});

        offer.setTaxes(taxes);

        Promotion promotion = new Promotion();
        promotion.setDescription("Black Friday Gardening Sale");
        promotion.setIdentifier("12345");
        offer.setPromotions(new Promotion[]{promotion});


        Fulfilment fulfilment1 = new Fulfilment();
        fulfilment1.setName("Next day click and collect");
        fulfilment1.setShortMessage("Order by 7pm for next day collection");
        fulfilment1.setLongMessage("Orders placed on Sunday will be available to collect from Tuesday");
        fulfilment1.setCode("CC1");
        fulfilment1.setMinDeliveryDays(1);
        fulfilment1.setMaxDeliveryDays(1);
        fulfilment1.setShippingPrice(0);
        Fulfilment.Availability availability1 = new Fulfilment.Availability();
        availability1.setStores(new String[]{"123", "456", "789"});
        fulfilment1.setAvailability(availability1);

        Fulfilment fulfilment2 = new Fulfilment();
        fulfilment2.setName("Standard Shipping");
        fulfilment2.setShortMessage("Choose your delivery date in checkout");
        fulfilment2.setLongMessage("Delivery from next working day");
        fulfilment2.setCode("STD");
        fulfilment2.setMinDeliveryDays(3);
        fulfilment2.setMaxDeliveryDays(5);
        fulfilment2.setShippingPrice(3.99f);
        Fulfilment.Availability availability2 = new Fulfilment.Availability();
        availability2.setZone("ESWM");
        fulfilment2.setAvailability(availability2);

        offer.setFulfilmentOptions(new Fulfilment[]{fulfilment1, fulfilment2});


        ObjectMapper mapper = new ObjectMapper();
        mapper.writerFor(Offer.class).writeValue(System.out, offer);
    }
}
