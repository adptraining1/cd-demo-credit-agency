package com.innoq.mploed.ddd.creditAgency;

import com.innoq.mploed.ddd.creditAgency.domain.Rating;
import org.junit.Test;
import static org.junit.Assert.*;

public class RatingServiceTest {
    @Test
    public void testMunichScore() {
        RatingService ratingService = new RatingService();
        Rating rating = ratingService.getRating("Whatever", "80331");
        assertEquals(110, rating.getPoints());
    }
    @Test
    public void testNuernbergScore() {
        RatingService ratingService = new RatingService();
        Rating rating = ratingService.getRating("Whatever", "90429");
        assertEquals(115, rating.getPoints());
    }

    @Test
    public void testStuttgartScore() {
        RatingService ratingService = new RatingService();
        Rating rating = ratingService.getRating("Whatever", "70178");
        assertEquals(50, rating.getPoints());
    }
}
