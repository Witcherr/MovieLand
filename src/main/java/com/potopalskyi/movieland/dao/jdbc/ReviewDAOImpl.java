package com.potopalskyi.movieland.dao.jdbc;

import com.potopalskyi.movieland.dao.ReviewDAO;
import com.potopalskyi.movieland.dao.jdbc.mapper.ReviewRowMapper;
import com.potopalskyi.movieland.entity.Review;
import com.potopalskyi.movieland.entity.ReviewAlterParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
        return jdbcTemplate.query(getReviewByMovieIdSQL, new Object[]{id}, reviewRowMapper);
    }

    @Override
    public boolean addReview(ReviewAlterParam reviewAlterParam) {
        int count = jdbcTemplate.queryForObject(checkReviewExistSQL, new Object[]{reviewAlterParam.getMovieId(), reviewAlterParam.getAuthorId()}, Integer.class);
        if (count == 0) {
            jdbcTemplate.update(addReviewSQL, reviewAlterParam.getMovieId(), reviewAlterParam.getAuthorId(), reviewAlterParam.getReview());
            logger.info("Review {} was inserted into database", reviewAlterParam);
            return true;
        }
        jdbcTemplate.update(updateReviewSQL, reviewAlterParam.getReview(), reviewAlterParam.getMovieId(), reviewAlterParam.getAuthorId());
        logger.info("Review {} was updated into database", reviewAlterParam);
        return true;
    }

    @Override
    public boolean deleteReview(ReviewAlterParam reviewAlterParam) {
        jdbcTemplate.update(deleteReviewSQL, reviewAlterParam.getMovieId(), reviewAlterParam.getAuthorId());
        logger.info("Review {} was deleted from database", reviewAlterParam);
        return true;
    }
}
