package com.potopalskyi.movieland.service;

import com.potopalskyi.movieland.entity.business.Genre;

import java.util.List;

public interface GenreService {
    List<Genre> getGenreById(int id);

    List<Genre> getGenreFromCacheByMovieId(int movieId);
}
