package com.potopalskyi.movieland.service.impl;

import com.potopalskyi.movieland.caching.RatingCache;
import com.potopalskyi.movieland.dao.RatingDAO;
import com.potopalskyi.movieland.entity.dto.TotalRatingDTO;
import com.potopalskyi.movieland.entity.param.RatingParam;
import com.potopalskyi.movieland.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RatingServiceImpl implements RatingService {

    @Autowired
    private RatingDAO ratingDAO;

    @Autowired
    private RatingCache ratingCache;

    @Override
    public void addRating(RatingParam ratingParam) {
        ratingDAO.addRating(ratingParam);
        ratingCache.addNewElement(ratingParam);
    }

    @Override
    public void addRatingToDAO(RatingParam ratingParam) {
        ratingDAO.addRating(ratingParam);
    }

    @Override
    public double getAverageRatingByMovieId(int movieId) {
        return ratingCache.getAverageRatingByMovieId(movieId);
    }

    @Override
    public TotalRatingDTO getTotalRating(int movieId) {
        return ratingDAO.getTotalRating(movieId);
    }

    @Override
    public double getUserRating(int userId, int movieId) {
        return ratingDAO.getUserRating(userId, movieId);
    }
}
