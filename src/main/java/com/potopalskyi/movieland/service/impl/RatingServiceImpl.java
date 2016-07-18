package com.potopalskyi.movieland.service.impl;

import com.potopalskyi.movieland.caching.RatingCache;
import com.potopalskyi.movieland.dao.RatingDAO;
import com.potopalskyi.movieland.entity.dto.RatingDTO;
import com.potopalskyi.movieland.entity.param.RatingParam;
import com.potopalskyi.movieland.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Service
public class RatingServiceImpl implements RatingService {

    @Autowired
    private RatingDAO ratingDAO;

    @Autowired
    private RatingCache ratingCache;

    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private Lock writeLock = readWriteLock.writeLock();

    @Override
    public void addRating(RatingParam ratingParam) {
        writeLock.lock();
        try {
            ratingDAO.addRating(ratingParam);
            ratingCache.addNewElement(ratingParam);
        } finally {
            writeLock.unlock();
        }

    }

    @Override
    public double getAverageRatingByMovieId(int movieId) {
        return ratingCache.getAverageRatingByMovieId(movieId);
    }

    @Override
    public double getUserRating(int userId, int movieId) {
        return ratingDAO.getUserRating(userId, movieId);
    }

    @Override
    public List<RatingDTO> getTotalRatingsForAllMovies() {
        return ratingDAO.getTotalRatingsForAllMovies();
    }

    @Override
    public void deleteRatings(int movieId) {
        writeLock.lock();
        try {
            ratingDAO.deleteRatings(movieId);
            ratingCache.deleteRatings(movieId);

        } finally {
            writeLock.unlock();
        }
    }
}
