package com.potopalskyi.movieland.caching;

import com.potopalskyi.movieland.entity.Genre;

import java.util.List;

public interface GenreCache {

    List<Genre> getGenreByMovieId(int movieId);

    void fillCache();

    List<Genre> addNewElementToCache(int movieId);
}
