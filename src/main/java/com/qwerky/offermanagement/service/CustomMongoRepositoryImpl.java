package com.qwerky.offermanagement.service;

import com.qwerky.offermanagement.model.Offer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

/**
 * Implementation of the MongoRepository extension.
 */
public class CustomMongoRepositoryImpl implements CustomMongoRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public boolean isCurrent(Offer offer) {
        Offer current = mongoTemplate.findById(offer.getId(), Offer.class);
        return offer.equals(current);
    }
}
