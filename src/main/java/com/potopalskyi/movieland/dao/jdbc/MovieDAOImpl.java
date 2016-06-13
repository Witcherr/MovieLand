package com.potopalskyi.movieland.dao.jdbc;

import com.potopalskyi.movieland.dao.MovieDAO;
import com.potopalskyi.movieland.dao.jdbc.mapper.MovieDeatailedRowMapper;
import com.potopalskyi.movieland.dao.jdbc.mapper.MovieRowMapper;
import com.potopalskyi.movieland.entity.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MovieDAOImpl implements MovieDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private String getAllMoviesSQL;

    @Autowired
    private String getMoviesByIdSQL;

    @Autowired
    private String getReviewByIdSQL;

    @Override
    public List<Movie> getAllMovies() {
        return jdbcTemplate.query(getAllMoviesSQL, new MovieRowMapper());
    }

    @Override
    public Movie getMovieById(int id) {
        return jdbcTemplate.queryForObject(getMoviesByIdSQL, new Object[]{id}, new MovieDeatailedRowMapper());
    }

    @Override
    public List<String> getReviewById(int id) {
        return jdbcTemplate.queryForList(getReviewByIdSQL, new Object[]{id}, String.class);
    }
}
