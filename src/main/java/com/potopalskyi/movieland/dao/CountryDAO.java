package com.potopalskyi.movieland.dao;

import com.potopalskyi.movieland.entity.business.Country;
import com.potopalskyi.movieland.entity.business.Movie;

import java.util.List;

public interface CountryDAO {

    List<Country> getCountryById(int id);

    void saveCountryForNewMovie(Movie movie);

    void deleteCountryForMovie(int movieId);
}
