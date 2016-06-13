package com.potopalskyi.movieland.service;

import com.potopalskyi.movieland.entity.Movie;
import org.springframework.stereotype.Service;

import java.util.List;

public interface MovieLandService {
    List<Movie> getAllMovies();

    Movie getMovieById(int id);
}
