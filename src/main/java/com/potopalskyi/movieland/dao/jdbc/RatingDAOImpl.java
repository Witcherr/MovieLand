package com.potopalskyi.movieland.dao.jdbc;

import com.potopalskyi.movieland.dao.RatingDAO;
import com.potopalskyi.movieland.dao.jdbc.mapper.RatingRowMapper;
import com.potopalskyi.movieland.dao.jdbc.mapper.TotalRatingMapper;
import com.potopalskyi.movieland.entity.dto.TotalRatingDTO;
import com.potopalskyi.movieland.entity.param.RatingParam;
import com.potopalskyi.movieland.entity.dto.RatingDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingDAOImpl implements RatingDAO {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private RatingRowMapper ratingRowMapper;

    @Autowired
    private TotalRatingMapper totalRatingMapper;

    @Autowired
    private String checkRatingExistSQL;

    @Autowired
    private String addRatingSQL;

    @Autowired
    private String updateRatingSQL;

    @Autowired
    private String getAllRatingSQL;

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
    public List<RatingDTO> getAllRating() {
        logger.info("Start getting ratings");
        return jdbcTemplate.query(getAllRatingSQL, ratingRowMapper);
    }

    @Override
    public TotalRatingDTO getTotalRating(int movieId) {
        return null;
    }
}
