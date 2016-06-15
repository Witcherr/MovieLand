package com.potopalskyi.movieland.service;

import com.potopalskyi.movieland.entity.Genre;
import com.potopalskyi.movieland.entity.Movie;

public interface GenreService {
    public Genre getByMovie(Movie movie);
}
