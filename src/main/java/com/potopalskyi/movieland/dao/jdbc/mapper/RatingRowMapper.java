package com.potopalskyi.movieland.dao.jdbc.mapper;

import com.potopalskyi.movieland.entity.dto.RatingDTO;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class RatingRowMapper implements RowMapper<RatingDTO> {

    @Override
    public RatingDTO mapRow(ResultSet resultSet, int i) throws SQLException {
        RatingDTO ratingDTO = new RatingDTO();
        ratingDTO.setMovieId(resultSet.getInt("movie_id"));
        ratingDTO.setUserId(resultSet.getInt("user_id"));
        ratingDTO.setRating(resultSet.getDouble("rating"));
        return ratingDTO;
    }
}
