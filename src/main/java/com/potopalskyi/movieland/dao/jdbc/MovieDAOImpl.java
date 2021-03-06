package com.potopalskyi.movieland.dao.jdbc;

import com.potopalskyi.movieland.dao.MovieDAO;
import com.potopalskyi.movieland.dao.jdbc.mapper.MovieDetailedRowMapper;
import com.potopalskyi.movieland.dao.jdbc.mapper.MovieIdRowMapper;
import com.potopalskyi.movieland.dao.jdbc.mapper.MovieRowMapper;
import com.potopalskyi.movieland.entity.business.Movie;
import com.potopalskyi.movieland.entity.param.MovieSearchParam;
import com.potopalskyi.movieland.entity.param.MovieSortAndLimitParam;
import com.potopalskyi.movieland.entity.exception.NoDataFoundException;
import com.potopalskyi.movieland.util.GeneratorSQLQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MovieDAOImpl implements MovieDAO {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private String getMoviesByIdSQL;

    @Autowired
    private String getAllMoviesIdeIdSQL;

    @Autowired
    private GeneratorSQLQuery generatorSQLQuery;

    @Autowired
    private MovieRowMapper movieRowMapper;

    @Autowired
    private MovieDetailedRowMapper movieDetailedRowMapper;

    @Autowired
    private MovieIdRowMapper movieIdRowMapper;

    @Override
    public List<Movie> getAllMovies(MovieSortAndLimitParam movieSortAndLimitParam) {
        logger.info("Start query for all movies");
        try {
            return jdbcTemplate.query(generatorSQLQuery.generateAllMoviesWithParamQuery(movieSortAndLimitParam), movieRowMapper);
        } catch (EmptyResultDataAccessException e) {
            logger.warn("Database of movies is empty");
            throw new NoDataFoundException("Database of movies is empty", e);
        }
    }

    @Override
    public List<Movie> getMoviesBySearch(MovieSearchParam movieSearchParam) {
        logger.info("Start query for getting movies with search params = {}", movieSearchParam);
        try {
            return jdbcTemplate.query(generatorSQLQuery.generateSearchMoviesQuery(movieSearchParam), movieRowMapper);
        } catch (EmptyResultDataAccessException e) {
            logger.warn("There are no movies with params = {}", movieSearchParam, movieSearchParam);
            throw new NoDataFoundException("There are no movies with params " + movieSearchParam, e);
        }
    }

    @Override
    public Movie getMovieById(int id) {
        logger.info("Start query for getting movie with id = {}", id);
        try {
            return jdbcTemplate.queryForObject(getMoviesByIdSQL, new Object[]{id}, movieDetailedRowMapper);
        } catch (EmptyResultDataAccessException e) {
            logger.warn("The movie with id = {} doesn't exist", id, e);
            throw new NoDataFoundException("The movie with id = " + id + " doesn't exist", e);
        }
    }

    @Override
    public List<Integer> getAllMoviesId() {
        logger.info("Start getting id for all movies");
        try {
            return jdbcTemplate.query(getAllMoviesIdeIdSQL, movieIdRowMapper);
        } catch (EmptyResultDataAccessException e) {
            logger.warn("Database of movies is empty");
            throw new NoDataFoundException("Database of movies is empty", e);
        }
    }
}
