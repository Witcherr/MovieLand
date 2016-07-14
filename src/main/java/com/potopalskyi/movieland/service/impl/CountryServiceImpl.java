package com.potopalskyi.movieland.service.impl;

import com.potopalskyi.movieland.caching.CountryCache;
import com.potopalskyi.movieland.dao.CountryDAO;
import com.potopalskyi.movieland.entity.business.Country;
import com.potopalskyi.movieland.entity.business.Movie;
import com.potopalskyi.movieland.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CountryServiceImpl implements CountryService{

    @Autowired
    private CountryDAO countryDAO;

    @Autowired
    private CountryCache countryCache;

    @Override
    public List<Country> getCountryById(int id) {
        return countryDAO.getCountryById(id);
    }

    @Override
    public List<Country> getCountryFromCacheByMovieId(int movieId) {
        return countryCache.getCountryByMovieId(movieId);
    }

    @Override
    public void saveCountryForNewMovie(Movie movie) {
        countryDAO.saveGenreForNewMovie(movie);
    }
}
