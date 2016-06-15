package com.potopalskyi.movieland.dao;

import com.potopalskyi.movieland.entity.Genre;
import com.potopalskyi.movieland.entity.Movie;

public interface GenreDAO {
    public Genre getByMovie(Movie movie);
}
