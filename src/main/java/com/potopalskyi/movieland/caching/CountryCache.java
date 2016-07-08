package com.potopalskyi.movieland.caching;

import com.potopalskyi.movieland.entity.business.Country;

import java.util.List;

public interface CountryCache {
    List<Country> getCountryByMovieId(int movieId);

    void fillCache();

    List<Country> addNewElementToCache(int movieId);
}
