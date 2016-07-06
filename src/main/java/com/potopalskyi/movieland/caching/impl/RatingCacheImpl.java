package com.potopalskyi.movieland.caching.impl;

import com.potopalskyi.movieland.caching.RatingCache;
import com.potopalskyi.movieland.entity.dto.TotalRatingDTO;
import com.potopalskyi.movieland.entity.param.RatingParam;
import com.potopalskyi.movieland.entity.dto.RatingDTO;
import com.potopalskyi.movieland.service.RatingService;
import com.potopalskyi.movieland.util.ConverterToDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Service
public class RatingCacheImpl implements RatingCache {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private Lock readLock = readWriteLock.readLock();
    private Lock writeLock = readWriteLock.writeLock();

    @Autowired
    private RatingService ratingService;

    private List<RatingDTO> ratingCacheList = new CopyOnWriteArrayList<>();

    @Scheduled(fixedRate = 60 * 1000)
    @Override
    public void flush() {
        writeLock.lock();
        try {
            logger.debug("Start flushing cache of ratings");
            if (!ratingCacheList.isEmpty()) {
                for(RatingDTO ratingDTO: ratingCacheList){
                    ratingService.addRatingToDAO(ConverterToDTO.convertToRatingParam(ratingDTO));
                }
                ratingCacheList.clear();
            }
            logger.debug("End flushing cache of ratings");
        }finally {
            writeLock.unlock();
        }
    }

    @Override
    public void addNewElement(RatingParam ratingParam) {
        writeLock.lock();
        try {
            boolean isExistInCache = false;
            for (RatingDTO ratingDTO : ratingCacheList) {
                if (ratingDTO.getMovieId() == ratingParam.getMovieId()
                        && ratingDTO.getUserId() == ratingParam.getAuthorId()) {
                    ratingDTO.setRating(ratingParam.getRating());
                    isExistInCache = true;
                    break;
                }
            }
            if (!isExistInCache) {
                RatingDTO ratingDTO = new RatingDTO();
                ratingDTO.setUserId(ratingParam.getAuthorId());
                ratingDTO.setMovieId(ratingParam.getMovieId());
                ratingDTO.setRating(ratingParam.getRating());
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
            if(!ratingCacheList.isEmpty()) {
                for (RatingDTO ratingDTO : ratingCacheList) {
                    if (movieId == ratingDTO.getMovieId()) {
                        sumRating += ratingDTO.getRating();
                        count++;
                    }
                }
            }
            TotalRatingDTO totalRatingDTO = ratingService.getTotalRating(movieId);
            if(totalRatingDTO != null) {
                sumRating += totalRatingDTO.getSumRating();
                count += totalRatingDTO.getCountRating();
            }
            if (count != 0) {
                return sumRating / count;
            }
            return 0;
        }finally {
            readLock.unlock();
        }
    }
}
