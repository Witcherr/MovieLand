package com.potopalskyi.movieland.dao.jdbc;

import com.potopalskyi.movieland.dao.RatingDAO;
import com.potopalskyi.movieland.dao.jdbc.mapper.TotalRatingMapper;
import com.potopalskyi.movieland.entity.dto.TotalRatingDTO;
import com.potopalskyi.movieland.entity.exception.NoDataFoundException;
import com.potopalskyi.movieland.entity.param.RatingParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

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
    private String updateRatingSQL;

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
        }
        jdbcTemplate.update(updateRatingSQL, ratingParam.getRating(), ratingParam.getMovieId(), ratingParam.getAuthorId());
        logger.info("Rating = {} was updated into database", ratingParam);
    }

    @Override
    public TotalRatingDTO getTotalRating(int movieId) {
        logger.info("Start getting total rating information from database for movieid = {}", movieId);
        try {
            return jdbcTemplate.queryForObject(getTotalRatingSQL, new Object[]{movieId}, totalRatingMapper);
        }catch (EmptyResultDataAccessException e){
                logger.warn("There is no information about rating in database for movieid = {}", movieId, e);
            return null;
        }
    }

    @Override
    public double getUserRating(int userId, int movieId) {
        logger.info("Start getting rating for movieid = {} for userid = {}", movieId, userId);
        try {
            return jdbcTemplate.queryForObject(getUserRatingForSQl, new Object[]{movieId, userId}, Double.class);
        }catch (EmptyResultDataAccessException e){
            logger.warn("There is no information about rating in database for movieid = {} for userId ={}", movieId, userId, e);
            return -1;
        }
    }
}
