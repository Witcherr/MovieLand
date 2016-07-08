package com.potopalskyi.movieland.dao.jdbc.mapper;

import com.potopalskyi.movieland.entity.business.Review;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ReviewRowMapperTest {

    @Test
    public void reviewRowMapperTest() throws SQLException {
        ResultSet resultSet = mock(ResultSet.class);
        when(resultSet.getString(anyString())).thenReturn("Best movie");

        ReviewRowMapper reviewRowMapper = new ReviewRowMapper();
        Review review = reviewRowMapper.mapRow(resultSet, 0);
        assertEquals("Best movie", review.getDescription());
    }
}
