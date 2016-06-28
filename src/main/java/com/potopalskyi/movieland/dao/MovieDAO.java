package com.potopalskyi.movieland.dao;

import com.potopalskyi.movieland.entity.Movie;
import com.potopalskyi.movieland.entity.MovieSearchParam;
import com.potopalskyi.movieland.entity.MovieSortAndLimitParam;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface MovieDAO {
    List<Movie> getAllMovies(MovieSortAndLimitParam movieSortAndLimitParam);

    List<Movie> getMoviesBySearch(MovieSearchParam movieSearchParam);

    Movie getMovieById(int id);

    List<Integer> getAllMoviesId();
}
