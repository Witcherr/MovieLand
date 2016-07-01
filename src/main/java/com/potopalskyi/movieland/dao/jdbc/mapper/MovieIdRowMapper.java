package com.potopalskyi.movieland.dao.jdbc.mapper;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class MovieIdRowMapper implements RowMapper<Integer>{
    @Override
    public Integer mapRow(ResultSet resultSet, int i) throws SQLException {
        return resultSet.getInt("id");
    }
}
