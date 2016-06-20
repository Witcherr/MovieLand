package com.potopalskyi.movieland.service;

import com.potopalskyi.movieland.entity.Movie;
import com.potopalskyi.movieland.entity.MovieSearchParam;
import org.springframework.stereotype.Service;

import java.util.List;

public interface MovieService {
    List<Movie> getAllMovies(String ratingOrder, String priceOrder);

    List<Movie> getMoviesBySearch(MovieSearchParam movieSearchParam);

    Movie getMovieById(int id);
}
