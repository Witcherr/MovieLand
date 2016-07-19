package com.potopalskyi.movieland.caching.impl;

import com.potopalskyi.movieland.caching.RatingCache;
import com.potopalskyi.movieland.entity.dto.RatingDTO;
import com.potopalskyi.movieland.entity.param.RatingParam;
import com.potopalskyi.movieland.service.RatingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Service
public class RatingCacheImpl implements RatingCache {

    private static final int ONE = 1;

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private Lock readLock = readWriteLock.readLock();
    private Lock writeLock = readWriteLock.writeLock();

    @Autowired
    private RatingService ratingService;

    private List<RatingDTO> ratingCacheList = new ArrayList<>();

    @PostConstruct
    @Override
    public void fillCache() {
        logger.debug("Start filling cache of ratings");
        List<RatingDTO> tempList = ratingService.getTotalRatingsForAllMovies();
        if (tempList != null) {
            ratingCacheList = new CopyOnWriteArrayList<>(tempList);
        }
        logger.debug("End filling cache of ratings");
    }

    @Override
    public void addNewElement(RatingParam ratingParam) {
        writeLock.lock();
        try {
            boolean isMovieExistInCache = false;
            for (RatingDTO ratingDTO : ratingCacheList) {
                if (ratingDTO.getMovieId() == ratingParam.getMovieId()) {
                    int count = ratingDTO.getCountRating();
                    double rating = ratingDTO.getSumRating() + ratingParam.getRating();
                    ratingDTO.setCountRating(++count);
                    ratingDTO.setSumRating(rating);
                    isMovieExistInCache = true;
                    break;
                }
            }
            if (!isMovieExistInCache) {
                RatingDTO ratingDTO = new RatingDTO();
                ratingDTO.setMovieId(ratingParam.getMovieId());
                ratingDTO.setSumRating(ratingParam.getRating());
                ratingDTO.setCountRating(ONE);
                ratingCacheList.add(ratingDTO);
            }
        } finally {
            writeLock.unlock();
        }
    }

    @Override
    public double getAverageRatingByMovieId(int movieId) {
        double sumRating = 0;
        int count = 0;
        readLock.lock();
        try {
            for (RatingDTO ratingDTO : ratingCacheList) {
                if (movieId == ratingDTO.getMovieId()) {
                    sumRating = ratingDTO.getSumRating();
                    count = ratingDTO.getCountRating();
                    break;
                }
            }
            if (count != 0) {
                return sumRating / count;
            }
            return 0;
        } finally {
            readLock.unlock();
        }
    }

    @Override
    public void deleteRatings(int movieId) {
        writeLock.lock();
        try {
            for (RatingDTO ratingDTO : ratingCacheList) {
                if (ratingDTO.getMovieId() == movieId) {
                    ratingCacheList.remove(ratingDTO);
                    break;
                }
            }
        } finally {
            writeLock.unlock();
        }
    }
}
