package com.potopalskyi.movieland.service.impl;

import com.potopalskyi.movieland.dao.CountryDAO;
import com.potopalskyi.movieland.entity.Country;
import com.potopalskyi.movieland.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CountryServiceImpl implements CountryService{

    @Autowired
    private CountryDAO countryDAO;

    @Override
    public List<Country> getCountryById(int id) {
        return countryDAO.getCountryById(id);
    }
}
