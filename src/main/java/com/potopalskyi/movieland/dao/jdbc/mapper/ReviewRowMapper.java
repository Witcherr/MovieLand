package com.potopalskyi.movieland.dao.jdbc.mapper;

import com.potopalskyi.movieland.entity.business.Review;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class ReviewRowMapper implements RowMapper<Review>{

    @Override
    public Review mapRow(ResultSet resultSet, int i) throws SQLException {
        Review review = new Review();
        review.setDescription(resultSet.getString("description"));
        return review;
    }
}
