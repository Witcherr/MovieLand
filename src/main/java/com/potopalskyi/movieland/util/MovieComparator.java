package com.potopalskyi.movieland.util;

import com.potopalskyi.movieland.entity.Movie;
import com.potopalskyi.movieland.entity.enums.Ordering;

import java.util.Comparator;

public class MovieComparator implements Comparator<Movie> {

    private String ratingOrder;
    private String priceOrder;

    public MovieComparator(String ratingOrder, String priceOrder) {
        if (Ordering.ASC.sortId().equals(ratingOrder) || Ordering.DESC.sortId().equals(ratingOrder)) {
            this.ratingOrder = ratingOrder;
        }
        if (Ordering.ASC.sortId().equals(priceOrder) || Ordering.DESC.sortId().equals(priceOrder)) {
            this.priceOrder = priceOrder;
        }
    }

    @Override
    public int compare(Movie movie1, Movie movie2) {
        int result = 0;
        double rating1 = movie1.getRating();
        double rating2 = movie2.getRating();
        double price1 = movie1.getPrice();
        double price2 = movie2.getPrice();
        if (ratingOrder != null) {
            if (Ordering.ASC.sortId().equals(ratingOrder)) {
                result = Double.compare(rating1, rating2);
            } else if (Ordering.DESC.sortId().equals(ratingOrder)) {
                result = -Double.compare(rating1, rating2);
            }
        }
        if (priceOrder != null && result == 0) {
            if (Ordering.ASC.sortId().equals(priceOrder)) {
                result = Double.compare(price1, price2);
            } else if (Ordering.DESC.sortId().equals(priceOrder)) {
                result = -Double.compare(price1, price2);
            }
        }
        return result;
    }
}
