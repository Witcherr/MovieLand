package com.potopalskyi.movieland.dao.jdbc;

import com.potopalskyi.movieland.dao.CountryDAO;
import com.potopalskyi.movieland.dao.jdbc.mapper.CountryRowMapper;
import com.potopalskyi.movieland.entity.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CountryDAOImpl implements CountryDAO{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private String getCountryByIdSQL;

    @Override
    public List<Country> getCountryById(int id) {
        return jdbcTemplate.query(getCountryByIdSQL, new Object[] {id}, new CountryRowMapper());
    }
}
