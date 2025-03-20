package com.qwerky.offermanagement.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qwerky.offermanagement.model.Offer;
import com.qwerky.offermanagement.rules.RuleService;
import com.qwerky.offermanagement.service.CandidateOfferRepository;
import com.qwerky.offermanagement.service.ValidOfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Kafka event manager for handling incoming events from candidate offer topic, and outgoing events to valid offers topic.
 */
@Component
public class EventManager {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Value("${kafka.topics.valid-offers}")
    private String validOffersTopic;

    @Value("${kafka.topics.candidate-offers}")
    private String candidateOffersTopic;

    @Autowired
    private RuleService ruleService;

    @Autowired
    private CandidateOfferRepository candidateOfferRepository;

    @Autowired
    private ValidOfferRepository validOfferRepository;


    @KafkaListener(topics = "#{@environment.getProperty('kafka.topics.candidate-offers')}")
    public void receive(final String message) throws JsonProcessingException {

        // Deserialise event
        ObjectMapper mapper = new ObjectMapper();
        Offer candidateOffer = mapper.readValue(message, Offer.class);

        // Persist as candidate offer
        candidateOfferRepository.save(candidateOffer);

        // Load all candidate offers from the database for the offer's EAN
        List<Offer> offers = candidateOfferRepository.findByEan(candidateOffer.getProduct().getEan());
        ruleService.fireRules(offers);

        // Persist any valid offers and send events
        for (Offer offer : offers) {
            if (offer.isValid()) {
                send(offer);
                validOfferRepository.save(offer);
            } else {
                // Check invalid offers to see if they were previously
                // valid, and if so update and send events
                if (validOfferRepository.existsById(offer.getId())) {
                    send(offer);
                    validOfferRepository.deleteById(offer.getId());
                }
            }
        }
    }


    public void send(final Offer offer) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            kafkaTemplate.send(validOffersTopic, mapper.writeValueAsString(offer));
        } catch (JsonProcessingException ex) {
            throw new RuntimeException(ex);
        }
    }

}
