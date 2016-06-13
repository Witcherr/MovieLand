package com.potopalskyi.movieland.dao.jdbc.mapper;

import com.potopalskyi.movieland.entity.Movie;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

public class MovieDeatailedRowMapper implements RowMapper<Movie> {
    @Override
    public Movie mapRow(ResultSet resultSet, int i) throws SQLException {
        Movie movie = new Movie();
        movie.setTitleRussian(resultSet.getString("name_rus"));
        movie.setTittleEnglish(resultSet.getString("name_eng"));
        movie.setYear(resultSet.getInt("year"));
        movie.setRating(resultSet.getDouble("rating"));
        movie.setDescription(resultSet.getString("description"));
        String genres = resultSet.getString("genreList");
        movie.setGenre(Arrays.asList(genres.split(",")));
        String countries = resultSet.getString("countryList");
        movie.setCountryList(Arrays.asList(countries.split(",")));
        return movie;
    }
}
