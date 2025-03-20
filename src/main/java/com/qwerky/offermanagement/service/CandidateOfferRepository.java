package com.qwerky.offermanagement.service;

import com.qwerky.offermanagement.model.Offer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

/**
 * MongoDB repository for data access of candidate offers.
 */
public interface CandidateOfferRepository extends MongoRepository<Offer, String> {

    /**
     * Find an offer by it's product EAN.
     * @param ean the ean to match against
     * @return a list of offers for the given product EAN
     */
    @Query("{product.ean : ?0}")
    List<Offer> findByEan(String ean);
}
