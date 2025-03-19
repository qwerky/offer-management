package com.qwerky.offermanagement.controller;

import com.qwerky.offermanagement.model.Offer;
import com.qwerky.offermanagement.service.ValidOfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/offer")
public class OfferController {

    @Autowired
    private ValidOfferRepository repository;

    @GetMapping("/{id}")
    public Offer getOffer(@PathVariable String id) {
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

}
