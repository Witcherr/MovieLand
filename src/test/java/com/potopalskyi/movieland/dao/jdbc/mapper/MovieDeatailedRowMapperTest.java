package com.potopalskyi.movieland.dao.jdbc.mapper;

import com.potopalskyi.movieland.entity.Movie;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MovieDeatailedRowMapperTest {
    @Test
    public void testMapRowWithProperMovie() throws SQLException {

        ResultSet resultSet = mock(ResultSet.class);
        //
        when(resultSet.getInt(any())).thenReturn(2016);
        when(resultSet.getString(any())).thenReturn("Super").
                thenReturn("Test").thenReturn("Супер фильм от создателей...").thenReturn("Action,AA").
                thenReturn("Бельгия,Франция");
        when(resultSet.getDouble(any())).thenReturn(9.2);

        MovieDeatailedRowMapper mapper = new MovieDeatailedRowMapper();
        Movie movie = mapper.mapRow(resultSet, 0);
        assertEquals("Super", movie.getTitleRussian());
        assertEquals("Test", movie.getTitleEnglish());
        assertEquals(2016, movie.getYear());
        assertEquals(9.2, movie.getRating(), 0.001);
        assertEquals("Супер фильм от создателей...", movie.getDescription());
        assertEquals(movie.getGenreList(), Arrays.asList("Action", "AA"));
        assertEquals(Arrays.asList("Бельгия", "Франция"), movie.getCountryList());
    }
}
