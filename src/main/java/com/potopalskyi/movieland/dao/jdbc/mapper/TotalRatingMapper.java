package com.potopalskyi.movieland.dao.jdbc.mapper;

import com.potopalskyi.movieland.entity.dto.TotalRatingDTO;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class TotalRatingMapper implements RowMapper<TotalRatingDTO>{

    @Override
    public TotalRatingDTO mapRow(ResultSet resultSet, int i) throws SQLException {
        TotalRatingDTO totalRatingDTO = new TotalRatingDTO();
        totalRatingDTO.setSumRating(resultSet.getDouble("sumrating"));
        totalRatingDTO.setCountRating(resultSet.getInt("countrating"));
        return totalRatingDTO;
    }
}
