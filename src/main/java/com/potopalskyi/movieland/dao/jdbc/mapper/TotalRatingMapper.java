package com.potopalskyi.movieland.dao.jdbc.mapper;

import com.potopalskyi.movieland.entity.dto.RatingDTO;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class TotalRatingMapper implements RowMapper<RatingDTO>{

    @Override
    public RatingDTO mapRow(ResultSet resultSet, int i) throws SQLException {
        RatingDTO ratingDTO = new RatingDTO();
        ratingDTO.setMovieId(resultSet.getInt("movie_id"));
        ratingDTO.setSumRating(resultSet.getDouble("sumrating"));
        ratingDTO.setCountRating(resultSet.getInt("countrating"));
        return ratingDTO;
    }
}
