package com.potopalskyi.movieland.service;

import com.potopalskyi.movieland.entity.business.Genre;
import com.potopalskyi.movieland.entity.business.Movie;

import java.util.List;

public interface GenreService {
    List<Genre> getGenreById(int id);

    List<Genre> getGenreFromCacheByMovieId(int movieId);

    int getGenreIdByName(String genreName);

    void saveGenreForNewMovie(Movie movie);

    void updateGenreForMovie(Movie movie);

    void deleteGenre(int movieId);
}
