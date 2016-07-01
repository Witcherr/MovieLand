package com.potopalskyi.movieland.service;

import com.potopalskyi.movieland.entity.Movie;
import com.potopalskyi.movieland.entity.MovieSearchParam;
import com.potopalskyi.movieland.entity.MovieSortAndLimitParam;

import java.util.List;

public interface MovieService {
    List<Movie> getAllMovies(MovieSortAndLimitParam movieSortAndLimitParam);

    List<Movie> getMoviesBySearch(MovieSearchParam movieSearchParam);

    Movie getMovieById(int id);

    List<Integer> getAllMoviesId();
}
