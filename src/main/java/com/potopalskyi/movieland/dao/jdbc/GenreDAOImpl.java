package com.potopalskyi.movieland.dao.jdbc;

import com.potopalskyi.movieland.dao.GenreDAO;
import com.potopalskyi.movieland.dao.jdbc.mapper.GenreRowMapper;
import com.potopalskyi.movieland.entity.business.Genre;
import com.potopalskyi.movieland.entity.business.Movie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

@Repository
public class GenreDAOImpl implements GenreDAO{

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private String getGenreByIdSQL;

    @Autowired
    private String getGenreIdByNameSQL;

    @Autowired
    private String addGenreForNewMovieSQL;

    @Autowired
    private GenreRowMapper genreRowMapper;

    @Override
    public List<Genre> getGenreById(int id) {
        logger.info("Start query for getting genre from database for movieId = {}", id);
        return jdbcTemplate.query(getGenreByIdSQL, new Object[] {id}, genreRowMapper);
    }

    @Override
    public int getGenreIdByName(String genreName) {
        logger.info("Start query for getting genreid from database for genre = {}", genreName);
        return jdbcTemplate.queryForObject(getGenreIdByNameSQL, new Object[]{genreName}, Integer.class);
    }

    @Override
    public void saveGenreForNewMovie(Movie movie) {
        logger.info("Start inserting into database genres for movie = {}", movie.getTitleEnglish());
        jdbcTemplate.batchUpdate(addGenreForNewMovieSQL, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
                Genre genre = movie.getGenreList().get(i);
                preparedStatement.setString(1, genre.getName());
                preparedStatement.setInt(2, movie.getId());
            }

            @Override
            public int getBatchSize() {
                return movie.getGenreList().size();
            }
        });
        logger.info("End inserting into database genres for movie = {}", movie.getTitleEnglish());
    }
}
