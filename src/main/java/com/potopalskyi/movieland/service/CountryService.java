package com.potopalskyi.movieland.service;

import com.potopalskyi.movieland.entity.Country;

import java.util.List;

public interface CountryService {

    public List<Country> getCountryById(int id);
}
