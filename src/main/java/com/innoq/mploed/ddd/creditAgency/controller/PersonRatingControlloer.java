package com.innoq.mploed.ddd.creditAgency.controller;

import com.codahale.metrics.Meter;
import com.codahale.metrics.MetricRegistry;
import com.innoq.mploed.ddd.creditAgency.RatingService;
import com.innoq.mploed.ddd.creditAgency.domain.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonRatingControlloer {
    private RatingService ratingService;

    private Meter scoringMeter;


    @Autowired
    public PersonRatingControlloer(RatingService ratingService, MetricRegistry metricRegistry) {
        this.ratingService = ratingService;
        this.scoringMeter = metricRegistry.meter("scorings");
    }

    @RequestMapping(method = RequestMethod.GET, path = "/personRating")
    public Rating ratePerson(
            @RequestParam(value = "firstName", defaultValue = "") String firstName,
            @RequestParam(value = "lastName", defaultValue = "") String lastName,
            @RequestParam(value = "street", defaultValue = "") String street,
            @RequestParam(value = "postCode", defaultValue = "") String postCode
    ) {
        Rating rating = ratingService.getRating(street, postCode);
        this.scoringMeter.mark();
        return rating;

    }


}
