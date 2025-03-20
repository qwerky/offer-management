package com.qwerky.offermanagement.service;

import com.qwerky.offermanagement.model.Offer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

/**
 * MongoDB repository for data access of valid offers.
 */
public interface ValidOfferRepository extends CustomMongoRepository, MongoRepository<Offer, String> {

}
