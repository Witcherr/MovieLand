package com.potopalskyi.movieland.service;

import com.potopalskyi.movieland.entity.business.Country;

import java.util.List;

public interface CountryService {

    List<Country> getCountryById(int id);

    List<Country> getCountryFromCacheByMovieId(int movieId);
}
