package com.potopalskyi.movieland.service;

import com.potopalskyi.movieland.entity.Movie;
import com.potopalskyi.movieland.entity.MovieSearchParam;
import com.potopalskyi.movieland.entity.MovieSortParam;

import java.util.List;

public interface MovieService {
    List<Movie> getAllMovies(MovieSortParam movieSortParam);

    List<Movie> getMoviesBySearch(MovieSearchParam movieSearchParam);

    Movie getMovieById(int id);
}
