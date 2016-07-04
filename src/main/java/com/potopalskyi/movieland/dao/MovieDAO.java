package com.potopalskyi.movieland.dao;

import com.potopalskyi.movieland.entity.business.Movie;
import com.potopalskyi.movieland.entity.param.MovieSearchParam;
import com.potopalskyi.movieland.entity.param.MovieSortAndLimitParam;

import java.util.List;

public interface MovieDAO {
    List<Movie> getAllMovies(MovieSortAndLimitParam movieSortAndLimitParam);

    List<Movie> getMoviesBySearch(MovieSearchParam movieSearchParam);

    Movie getMovieById(int id);

    List<Integer> getAllMoviesId();
}
