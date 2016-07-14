package com.potopalskyi.movieland.dao;

import com.potopalskyi.movieland.entity.business.Genre;
import com.potopalskyi.movieland.entity.business.Movie;

import java.util.List;

public interface GenreDAO {

    List<Genre> getGenreById(int id);

    int getGenreIdByName(String genreName);

    void saveGenreForNewMovie(Movie movie);
}
