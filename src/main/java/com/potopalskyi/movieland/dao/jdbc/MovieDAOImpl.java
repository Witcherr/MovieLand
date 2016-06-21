package com.potopalskyi.movieland.dao.jdbc;

import com.potopalskyi.movieland.dao.MovieDAO;
import com.potopalskyi.movieland.dao.jdbc.mapper.MovieDetailedRowMapper;
import com.potopalskyi.movieland.dao.jdbc.mapper.MovieRowMapper;
import com.potopalskyi.movieland.entity.Movie;
import com.potopalskyi.movieland.entity.MovieSearchParam;
import com.potopalskyi.movieland.util.GeneratorSQL;
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
    private GeneratorSQL generatorSQL;

    @Autowired
    private MovieRowMapper movieRowMapper;

    @Autowired
    private MovieDetailedRowMapper movieDetailedRowMapper;

    @Override
    public List<Movie> getAllMovies() {
        return jdbcTemplate.query(getAllMoviesSQL, movieRowMapper);
    }

    @Override
    public List<Movie> getMoviesBySearch(MovieSearchParam movieSearchParam) {
        return jdbcTemplate.query(generatorSQL.generateSearchMovies(movieSearchParam), movieRowMapper);
    }

    @Override
    public Movie getMovieById(int id) {
        return jdbcTemplate.queryForObject(getMoviesByIdSQL, new Object[]{id}, movieDetailedRowMapper);
    }

}
