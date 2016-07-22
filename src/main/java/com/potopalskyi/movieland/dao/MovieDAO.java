package com.potopalskyi.movieland.dao;

import com.potopalskyi.movieland.entity.business.Movie;
import com.potopalskyi.movieland.entity.param.MovieSearchParam;
import com.potopalskyi.movieland.entity.param.MovieSortLimitCurrencyParam;

import java.util.List;

public interface MovieDAO {
    List<Movie> getAllMovies(MovieSortLimitCurrencyParam movieSortLimitCurrencyParam);

    List<Movie> getMoviesBySearch(MovieSearchParam movieSearchParam);

    Movie getMovieById(int id);

    List<Integer> getAllMoviesId();

    void saveNewMovie(Movie movie);

    void updateMovie(Movie movie);

    void deleteMovie(int movieId);

    boolean checkExist(int movieId);

    byte[] getMoviePoster(int movieId);
}
