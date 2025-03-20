package com.qwerky.offermanagement;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qwerky.offermanagement.kafka.EventManager;
import com.qwerky.offermanagement.model.Offer;
import com.qwerky.offermanagement.model.Pricing;
import com.qwerky.offermanagement.model.Product;
import com.qwerky.offermanagement.model.Seller;
import com.qwerky.offermanagement.service.CandidateOfferRepository;
import com.qwerky.offermanagement.service.ValidOfferRepository;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

@SpringBootTest
@DirtiesContext
@ExtendWith(MockitoExtension.class)
public class RuleTest {

    @Autowired
    @InjectMocks
    private EventManager eventManager;

    @Value("${kafka.topics.valid-offers}")
    private String validOffersTopic;

    @Value("${kafka.topics.candidate-offers}")
    private String candidateOffersTopic;

    @Mock
    private KafkaTemplate<String, String> kafkaTemplate;

    @Mock
    private CandidateOfferRepository candidateOfferRepository;

    @Mock
    private ValidOfferRepository validOfferRepository;

    /**
     * This tests that a banner offer will replace a marketplace offer for the same product.
     * @throws JsonProcessingException
     */
    @Test
    public void testBannerRule() throws JsonProcessingException {
        Product product = new Product();
        product.setEan("51234567");

        Pricing pricing = new Pricing();
        pricing.setCurrentPrice(10.00f);

        Seller bannerSeller = new Seller();
        bannerSeller.setName("BQUK");
        Offer bannerOffer = new Offer();
        bannerOffer.setId("b");
        bannerOffer.setProduct(product);
        bannerOffer.setSeller(bannerSeller);
        bannerOffer.setPricing(pricing);

        Seller marketplaceSeller = new Seller();
        marketplaceSeller.setName("ACME supplies");
        Offer marketplaceOffer = new Offer();
        marketplaceOffer.setId("m");
        marketplaceOffer.setProduct(product);
        marketplaceOffer.setSeller(marketplaceSeller);
        marketplaceOffer.setPricing(pricing);

        // Prepare Mocks
        ArgumentCaptor<Offer> offerCaptor = ArgumentCaptor.forClass(Offer.class);

        // candidateOfferRepository should get a findByEan() with the product EAN, and should return the marketplaceOffer
        List<Offer> candidateOffers = List.of(marketplaceOffer, bannerOffer);
        when(candidateOfferRepository.findByEan(product.getEan())).thenReturn(candidateOffers);

        // validOfferRepository should get a existsById with the marketplace offer id and return true
        when(validOfferRepository.existsById(marketplaceOffer.getId())).thenReturn(true);

        // Execute test
        String message = new ObjectMapper().writeValueAsString(bannerOffer);
        eventManager.receive(message);

        // candidateOfferRepository should get a save() with the bannerOffer
        verify(candidateOfferRepository).save(offerCaptor.capture());
        assertEquals(offerCaptor.getValue().getId(), bannerOffer.getId());

        // validOfferRepository should get a save with the bannerOffer, and the offer should be valid
        verify(validOfferRepository).save(offerCaptor.capture());
        assertTrue(offerCaptor.getValue().isValid());
        assertEquals(offerCaptor.getValue().getId(), bannerOffer.getId());

        // validOfferRepository should get a deleteById with the marketplace offer id
        verify(validOfferRepository).deleteById(marketplaceOffer.getId());

        // kafkaTemplate should send both offers to the validOffersTopic
        ArgumentCaptor<String> stringCaptor = ArgumentCaptor.forClass(String.class);
        verify(kafkaTemplate, times(2)).send(eq(validOffersTopic), any());
    }
}
