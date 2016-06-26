package com.potopalskyi.movieland.util;

import com.potopalskyi.movieland.entity.Movie;
import com.potopalskyi.movieland.entity.MovieSortParam;
import com.potopalskyi.movieland.entity.enums.SortType;

import java.util.Comparator;

public class MovieComparator implements Comparator<Movie> {

    private MovieSortParam movieSortParam;

    public MovieComparator(MovieSortParam movieSortParam) {
        this.movieSortParam = movieSortParam;
    }

    @Override
    public int compare(Movie movie1, Movie movie2) {
        int result = 0;
        double rating1 = movie1.getRating();
        double rating2 = movie2.getRating();
        double price1 = movie1.getPrice();
        double price2 = movie2.getPrice();
        if (movieSortParam.getRatingSortType() != null) {
            if (SortType.ASC.sortId().equals(movieSortParam.getRatingSortType())) {
                result = Double.compare(rating1, rating2);
            } else if (SortType.DESC.sortId().equals(movieSortParam.getRatingSortType())) {
                result = -Double.compare(rating1, rating2);
            }
        }
        if ( movieSortParam.getPriceSortType() != null && result == 0) {
            if (SortType.ASC.sortId().equals(movieSortParam.getPriceSortType())) {
                result = Double.compare(price1, price2);
            } else if (SortType.DESC.sortId().equals(movieSortParam.getPriceSortType())) {
                result = -Double.compare(price1, price2);
            }
        }
        return result;
    }
}
