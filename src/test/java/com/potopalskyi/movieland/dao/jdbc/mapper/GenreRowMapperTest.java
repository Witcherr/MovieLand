package com.potopalskyi.movieland.dao.jdbc.mapper;

import com.potopalskyi.movieland.entity.business.Genre;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GenreRowMapperTest {

    @Test
    public void countryRowMapperTest() throws SQLException {
        ResultSet resultSet = mock(ResultSet.class);
        when(resultSet.getInt(anyString())).thenReturn(1);
        when(resultSet.getString(anyString())).thenReturn("Detective");

        GenreRowMapper genreRowMapper = new GenreRowMapper();
        Genre genre = genreRowMapper.mapRow(resultSet, 0);
        assertEquals(1, genre.getId());
        assertEquals("Detective", genre.getName());
    }
}
