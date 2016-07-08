package com.potopalskyi.movieland.dao.jdbc;

import com.potopalskyi.movieland.dao.CountryDAO;
import com.potopalskyi.movieland.dao.jdbc.mapper.CountryRowMapper;
import com.potopalskyi.movieland.entity.business.Country;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CountryDAOImpl implements CountryDAO{

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private String getCountryByIdSQL;

    @Autowired
    private CountryRowMapper countryRowMapper;

    @Override
    public List<Country> getCountryById(int id) {
        logger.info("Start query for getting country for movieid = {}", id);
        return jdbcTemplate.query(getCountryByIdSQL, new Object[] {id}, countryRowMapper);
    }
}
