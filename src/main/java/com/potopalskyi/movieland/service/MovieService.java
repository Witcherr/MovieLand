package com.potopalskyi.movieland.service;

import com.potopalskyi.movieland.entity.business.Movie;
import com.potopalskyi.movieland.entity.dto.MovieDetailedDTO;
import com.potopalskyi.movieland.entity.param.MovieSearchParam;
import com.potopalskyi.movieland.entity.param.MovieSortLimitCurrencyParam;

import java.util.List;

public interface MovieService {
    List<Movie> getAllMovies(MovieSortLimitCurrencyParam movieSortLimitCurrencyParam);

    List<Movie> getMoviesBySearch(MovieSearchParam movieSearchParam);

    MovieDetailedDTO getMovieById(int id);

    List<Integer> getAllMoviesId();

    void setUserRatingForMovie(MovieDetailedDTO movieDetailedDTO, String token, int movieId);

    void addNewMovie(Movie movie);

    void updateMovie(Movie movie);

    void markMovie(int movieId);

    void unMarkMovie(int movieId);

    void deleteMarkedMovies();

    byte[] getMoviePoster(int movieId);

    void setCurrency(MovieDetailedDTO movieDetailedDTO, String currency);
}
