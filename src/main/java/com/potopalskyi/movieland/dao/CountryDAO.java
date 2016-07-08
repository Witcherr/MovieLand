package com.potopalskyi.movieland.dao;

import com.potopalskyi.movieland.entity.business.Country;

import java.util.List;

public interface CountryDAO {

    List<Country> getCountryById(int id);
}
