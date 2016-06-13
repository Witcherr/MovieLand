package com.potopalskyi.movieland.dao;

import com.potopalskyi.movieland.entity.Movie;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface MovieDAO {
    List<Movie> getAllMovies();

    Movie getMovieById(int i);

    List<String> getReviewById(int i);
}
