package com.potopalskyi.movieland.service;

import com.potopalskyi.movieland.entity.business.Movie;
import com.potopalskyi.movieland.entity.dto.MovieDetailedDTO;
import com.potopalskyi.movieland.entity.param.MovieSearchParam;
import com.potopalskyi.movieland.entity.param.MovieSortAndLimitParam;

import java.util.List;

public interface MovieService {
    List<Movie> getAllMovies(MovieSortAndLimitParam movieSortAndLimitParam);

    List<Movie> getMoviesBySearch(MovieSearchParam movieSearchParam);

    MovieDetailedDTO getMovieById(int id);

    List<Integer> getAllMoviesId();

    void updateUserRating(Movie movie, int userId);
}
