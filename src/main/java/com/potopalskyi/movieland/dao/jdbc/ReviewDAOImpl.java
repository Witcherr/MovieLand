package com.potopalskyi.movieland.dao.jdbc;

import com.potopalskyi.movieland.dao.ReviewDAO;
import com.potopalskyi.movieland.dao.jdbc.mapper.ReviewRowMapper;
import com.potopalskyi.movieland.entity.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class ReviewDAOImpl implements ReviewDAO{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private String getReviewByMovieIdSQL;

    @Autowired
    private ReviewRowMapper reviewRowMapper;

    @Override
    public List<Review> getReviewByMovieId(int id) {
        return jdbcTemplate.query(getReviewByMovieIdSQL, new Object[]{id}, reviewRowMapper);
    }
}
