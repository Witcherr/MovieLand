package com.potopalskyi.movieland.util;

import com.potopalskyi.movieland.entity.Movie;
import com.potopalskyi.movieland.entity.enums.SortType;

import java.util.Comparator;

public class MovieComparator implements Comparator<Movie> {

    private String ratingOrder;
    private String priceOrder;

    public MovieComparator(String ratingOrder, String priceOrder) {
        if (SortType.ASC.sortId().equals(ratingOrder) || SortType.DESC.sortId().equals(ratingOrder)) {
            this.ratingOrder = ratingOrder;
        }
        if (SortType.ASC.sortId().equals(priceOrder) || SortType.DESC.sortId().equals(priceOrder)) {
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
            if (SortType.ASC.sortId().equals(ratingOrder)) {
                result = Double.compare(rating1, rating2);
            } else if (SortType.DESC.sortId().equals(ratingOrder)) {
                result = -Double.compare(rating1, rating2);
            }
        }
        if (priceOrder != null && result == 0) {
            if (SortType.ASC.sortId().equals(priceOrder)) {
                result = Double.compare(price1, price2);
            } else if (SortType.DESC.sortId().equals(priceOrder)) {
                result = -Double.compare(price1, price2);
            }
        }
        return result;
    }
}
