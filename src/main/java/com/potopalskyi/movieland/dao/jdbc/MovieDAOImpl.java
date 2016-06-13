package com.potopalskyi.movieland.dao.jdbc;

import com.potopalskyi.movieland.dao.MovieDAO;
import com.potopalskyi.movieland.dao.jdbc.mapper.MovieDeatailedRowMapper;
import com.potopalskyi.movieland.dao.jdbc.mapper.MovieRowMapper;
import com.potopalskyi.movieland.entity.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class MovieDAOImpl implements MovieDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private String sqlGetAllMovies;

    @Autowired
    private String sqlGetMoviesById;

    @Autowired
    private String sqlGetReviewById;

    @Override
    public List<Movie> getAllMovies() {
        return jdbcTemplate.query(sqlGetAllMovies, new MovieRowMapper());
    }

    @Override
    public Movie getMovieById(int i) {
        Movie movie = jdbcTemplate.queryForObject(sqlGetMoviesById, new Object[]{i}, new MovieDeatailedRowMapper());
        return movie;
    }

    @Override
    public List<String> getReviewById(int i) {
        return jdbcTemplate.queryForList(sqlGetReviewById, new Object[]{i}, String.class);
    }
}
