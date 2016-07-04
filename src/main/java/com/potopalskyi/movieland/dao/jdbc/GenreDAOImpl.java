package com.potopalskyi.movieland.dao.jdbc;

import com.potopalskyi.movieland.dao.GenreDAO;
import com.potopalskyi.movieland.dao.jdbc.mapper.GenreRowMapper;
import com.potopalskyi.movieland.entity.Genre;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GenreDAOImpl implements GenreDAO{

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private String getGenreByIdSQL;

    @Autowired
    private GenreRowMapper genreRowMapper;

    @Override
    public List<Genre> getGenreById(int id) {
        logger.info("Start query for getting genre from database for movieId = {}", id);
        return jdbcTemplate.query(getGenreByIdSQL, new Object[] {id}, genreRowMapper);
    }
}
