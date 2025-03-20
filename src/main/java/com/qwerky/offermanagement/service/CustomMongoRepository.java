package com.qwerky.offermanagement.service;

import com.qwerky.offermanagement.model.Offer;

/**
 * MongoRepository extension.
 */
public interface CustomMongoRepository {

    /**
     * Checks if the given offer is current.
     * @param offer the offer to check
     * @return true if the given offer exists and is indentical in data to that persisted. Returns false if
     * the offer doesn't exist, or exists but has any data point that is different.
     */
    boolean isCurrent(Offer offer);
}
