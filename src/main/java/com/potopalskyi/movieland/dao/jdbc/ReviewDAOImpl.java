package com.potopalskyi.movieland.dao.jdbc;

import com.potopalskyi.movieland.dao.ReviewDAO;
import com.potopalskyi.movieland.dao.jdbc.mapper.ReviewRowMapper;
import com.potopalskyi.movieland.entity.business.Review;
import com.potopalskyi.movieland.entity.param.ReviewAlterParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReviewDAOImpl implements ReviewDAO {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private String getReviewByMovieIdSQL;

    @Autowired
    private String addReviewSQL;

    @Autowired
    private String checkReviewExistSQL;

    @Autowired
    private String updateReviewSQL;

    @Autowired
    private String deleteReviewSQL;

    @Autowired
    private ReviewRowMapper reviewRowMapper;

    @Override
    public List<Review> getReviewByMovieId(int id) {
        logger.info("Start getting reviews for movieId = {}", id);
        try {
            return jdbcTemplate.query(getReviewByMovieIdSQL, new Object[]{id}, reviewRowMapper);
        } catch (EmptyResultDataAccessException e) {
            logger.warn("There are no reviews for movieId = {}", id, e);
            return null;
        }
    }

    @Override
    public void addReview(ReviewAlterParam reviewAlterParam) {
        logger.info("Start insert/update review {} into database", reviewAlterParam);
        int count = jdbcTemplate.queryForObject(checkReviewExistSQL, new Object[]{reviewAlterParam.getMovieId(), reviewAlterParam.getAuthorId()}, Integer.class);
        if (count == 0) {
            jdbcTemplate.update(addReviewSQL, reviewAlterParam.getMovieId(), reviewAlterParam.getAuthorId(), reviewAlterParam.getReview());
            logger.info("Review = {} was inserted into database", reviewAlterParam);
        }
        jdbcTemplate.update(updateReviewSQL, reviewAlterParam.getReview(), reviewAlterParam.getMovieId(), reviewAlterParam.getAuthorId());
        logger.info("Review = {} was updated into database", reviewAlterParam);
    }

    @Override
    public void deleteReview(ReviewAlterParam reviewAlterParam) {
        logger.info("Start deleting review = {} from database", reviewAlterParam);
        jdbcTemplate.update(deleteReviewSQL, reviewAlterParam.getMovieId(), reviewAlterParam.getAuthorId());
        logger.info("Review = {} was deleted from database", reviewAlterParam);
    }
}
