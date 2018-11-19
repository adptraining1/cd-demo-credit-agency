package com.innoq.mploed.ddd.creditAgency;

import com.codahale.metrics.Meter;
import com.codahale.metrics.MetricRegistry;
import com.innoq.mploed.ddd.creditAgency.domain.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RatingService {


    public Rating getRating(String street, String postCode) {
        int points = 0;

        Rating rating = new Rating();

        if(postCode.startsWith("8")) {
            points += 110;
        } else if(postCode.startsWith("9")) {
            points += 115;
        } else if(postCode.startsWith("0")) {
            points += 15;
        } else if(postCode.startsWith("1")) {
            points += 15;
        } else if(postCode.startsWith("2")) {
            points += 39;
        } else if(postCode.startsWith("5")) {
            points += 40;
        } else if(postCode.startsWith("6")) {
            points += 19;
        } else if(postCode.startsWith("7")) {
            points += 50;
        } else if(postCode.startsWith("4")) {
            points += 16;
        }

        if(street.contains("Kreuz")) {
            points += 20;
        }

        rating.setPoints(points);
        if(points <= 20) {
            rating.setColor("RED");
        } else if(points <= 40) {
            rating.setColor("YELLOW");
        } else if(points > 40){
            rating.setColor("GREEN");
        } else {
            rating.setColor("BLACK");
        }

        return rating;
    }
}
