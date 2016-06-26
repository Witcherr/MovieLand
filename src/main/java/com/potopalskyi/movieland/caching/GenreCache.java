package com.potopalskyi.movieland.caching;

import com.potopalskyi.movieland.entity.Genre;

import java.util.List;

public interface GenreCache {

    List<Genre> getGenreById(int movieId);

    void fillCache();

    void updateCache(int movieId);
}
