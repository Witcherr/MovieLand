package com.potopalskyi.movieland.dao;

import com.potopalskyi.movieland.entity.Movie;
import com.potopalskyi.movieland.entity.MovieSearchParam;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface MovieDAO {
    List<Movie> getAllMovies();

    List<Movie> getMoviesBySearch(MovieSearchParam movieSearchParam);

    Movie getMovieById(int id);

    List<Integer> getAllMoviesId();
}
