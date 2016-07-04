package com.potopalskyi.movieland.service.impl;

import com.potopalskyi.movieland.caching.RatingCache;
import com.potopalskyi.movieland.dao.RatingDAO;
import com.potopalskyi.movieland.entity.param.RatingParam;
import com.potopalskyi.movieland.entity.dto.RatingDTO;
import com.potopalskyi.movieland.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingServiceImpl implements RatingService {

    @Autowired
    private RatingDAO ratingDAO;

    @Autowired
    private RatingCache ratingCache;

    @Override
    public boolean addRating(RatingParam ratingParam) {
        if(ratingDAO.addRating(ratingParam)){
            ratingCache.addNewElement(ratingParam);
        }
        return true;
    }

    @Override
    public List<RatingDTO> getAllRating() {
        return ratingDAO.getAllRating();
    }

    @Override
    public double getAverageRatingByMovieId(int movieId) {
        return ratingCache.getAverageRatingByMovieId(movieId);
    }
}
