package com.potopalskyi.movieland.dao.jdbc;

import com.potopalskyi.movieland.dao.GenreDAO;
import com.potopalskyi.movieland.dao.jdbc.mapper.GenreRowMapper;
import com.potopalskyi.movieland.entity.Genre;
import com.potopalskyi.movieland.entity.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GenreDAOImpl implements GenreDAO{

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    String getGenreByMovieSQL;

    @Override
    public List<Genre> getByMovie(Movie movie) {
        return jdbcTemplate.query(getGenreByMovieSQL, new Object[] {movie.getId()}, new GenreRowMapper());
    }
}
