package com.potopalskyi.movieland.dao.jdbc;

import com.potopalskyi.movieland.dao.RatingDAO;
import com.potopalskyi.movieland.dao.jdbc.mapper.TotalRatingMapper;
import com.potopalskyi.movieland.entity.dto.RatingDTO;
import com.potopalskyi.movieland.entity.param.RatingParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RatingDAOImpl implements RatingDAO {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private TotalRatingMapper totalRatingMapper;

    @Autowired
    private String checkRatingExistSQL;

    @Autowired
    private String addRatingSQL;

    @Autowired
    private String deleteRatingSQL;

    @Autowired
    private String getTotalRatingSQL;

    @Autowired
    private String getUserRatingForSQl;

    @Override
    public void addRating(RatingParam ratingParam) {
        logger.info("Start insert/update rating = {} into database", ratingParam);
        int count = jdbcTemplate.queryForObject(checkRatingExistSQL, new Object[]{ratingParam.getMovieId(), ratingParam.getAuthorId()}, Integer.class);
        if (count == 0) {
            jdbcTemplate.update(addRatingSQL, ratingParam.getMovieId(), ratingParam.getAuthorId(), ratingParam.getRating());
            logger.info("Rating = {} was inserted into database", ratingParam);
        } else {
            logger.warn("Rating for movie = {} for user = {} has already exist in database", ratingParam.getMovieId(), ratingParam.getAuthorId());
            throw new RuntimeException("Rating for movie = " + ratingParam.getMovieId() + "has already exist");
        }
    }

    @Override
    public double getUserRating(int userId, int movieId) {
        logger.info("Start getting rating for movieid = {} for userid = {}", movieId, userId);
        try {
            return jdbcTemplate.queryForObject(getUserRatingForSQl, new Object[]{movieId, userId}, Double.class);
        } catch (EmptyResultDataAccessException e) {
            logger.warn("There is no information about rating in database for movieid = {} for userId ={}", movieId, userId, e);
            return -1;
        }
    }

    @Override
    public List<RatingDTO> getTotalRatingsForAllMovies() {
        logger.info("Start getting total rating information for all movies");
        try {
            return jdbcTemplate.query(getTotalRatingSQL, totalRatingMapper);
        } catch (EmptyResultDataAccessException e){
            logger.warn("Table of movie's rating is empty");
            return null;
        }
    }

    @Override
    public void deleteRatings(int movieId) {
        logger.info("Start deleting ratings for movieId = {}", movieId);
        jdbcTemplate.update(deleteRatingSQL, movieId);
        logger.info("End deleting ratings for movieId = {}", movieId);
    }
}
