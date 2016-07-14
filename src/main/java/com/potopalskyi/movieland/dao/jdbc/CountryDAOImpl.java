package com.potopalskyi.movieland.dao.jdbc;

import com.potopalskyi.movieland.dao.CountryDAO;
import com.potopalskyi.movieland.dao.jdbc.mapper.CountryRowMapper;
import com.potopalskyi.movieland.entity.business.Country;
import com.potopalskyi.movieland.entity.business.Movie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Repository
public class CountryDAOImpl implements CountryDAO{

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private String getCountryByIdSQL;

    @Autowired
    private String addCountryForNewMovieSQL;

    @Autowired
    private CountryRowMapper countryRowMapper;

    @Override
    public List<Country> getCountryById(int id) {
        logger.info("Start query for getting country for movieid = {}", id);
        return jdbcTemplate.query(getCountryByIdSQL, new Object[] {id}, countryRowMapper);
    }

    @Override
    public void saveGenreForNewMovie(Movie movie) {
        logger.info("Start inserting countries for movie = {}", movie.getTitleEnglish());
        jdbcTemplate.batchUpdate(addCountryForNewMovieSQL, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
                Country country = movie.getCountryList().get(i);
                preparedStatement.setInt(1, movie.getId());
                preparedStatement.setString(2, country.getName());
            }

            @Override
            public int getBatchSize() {
                return movie.getCountryList().size();
            }
        });
        logger.info("End inserting countries for movie = {}", movie.getTitleEnglish());
    }
}
