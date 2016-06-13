package com.potopalskyi.movieland.dao.jdbc.mapper;

import com.potopalskyi.movieland.entity.Movie;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

public class MovieRowMapper implements RowMapper<Movie> {

    @Override
    public Movie mapRow(ResultSet resultSet, int i) throws SQLException {
        Movie movie = new Movie();
        movie.setTitleRussian(resultSet.getString("name_rus"));
        movie.setTitleEnglish(resultSet.getString("name_eng"));
        movie.setYear(resultSet.getInt("year"));
        movie.setRating(resultSet.getDouble("rating"));
        String genres = resultSet.getString("genreList");
        movie.setGenreList(Arrays.asList(genres.split(",")));
        return movie;
    }
}
