package com.potopalskyi.movieland.dao;

import com.potopalskyi.movieland.entity.Country;

import java.util.List;

public interface CountryDAO {

    public List<Country> getCountryById(int id);
}
