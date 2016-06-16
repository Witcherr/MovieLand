package com.potopalskyi.movieland.dao.jdbc.mapper;

import com.potopalskyi.movieland.entity.Movie;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MovieDetailedRowMapperTest {
    @Test
    public void mapRowWithProperMovieTest() throws SQLException {
        ResultSet resultSet = mock(ResultSet.class);
        when(resultSet.getInt(anyString())).thenReturn(2016);
        when(resultSet.getString(anyString())).thenReturn("Super").
                thenReturn("Test").thenReturn("Супер фильм от создателей...");
        when(resultSet.getDouble(anyString())).thenReturn(9.2);

        MovieDetailedRowMapper mapper = new MovieDetailedRowMapper();
        Movie movie = mapper.mapRow(resultSet, 0);
        assertEquals("Super", movie.getTitleRussian());
        assertEquals("Test", movie.getTitleEnglish());
        assertEquals(2016, movie.getYear());
        assertEquals(9.2, movie.getRating(), 0.001);
        assertEquals("Супер фильм от создателей...", movie.getDescription());
    }
}
