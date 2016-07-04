package com.potopalskyi.movieland.caching.impl;

import com.potopalskyi.movieland.caching.RatingCache;
import com.potopalskyi.movieland.entity.param.RatingParam;
import com.potopalskyi.movieland.entity.dto.RatingDTO;
import com.potopalskyi.movieland.service.RatingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
public class RatingCacheImpl implements RatingCache {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private RatingService ratingService;

    private List<RatingDTO> ratingCacheList = new CopyOnWriteArrayList<>();

    @Scheduled(fixedRate = 365 * 24 * 60 * 60 * 1000)
    @Override
    public void fillCache() {
        logger.debug("Start filling of cache for rating");
        List<RatingDTO> temlList = ratingService.getAllRating();
        if(temlList != null){
            ratingCacheList = new CopyOnWriteArrayList<>(temlList);
        }
        logger.debug("End filling of cache for rating");
    }

    @Override
    public boolean addNewElement(RatingParam ratingParam) {
        for(RatingDTO ratingDTO: ratingCacheList){
            if (ratingDTO.getMovieId() == ratingParam.getMovieId()
                    && ratingDTO.getUserId() == ratingParam.getAuthorId()){
                ratingDTO.setRating(ratingParam.getRating());
                return true;
            }
        }
        RatingDTO ratingDTO = new RatingDTO();
        ratingDTO.setUserId(ratingParam.getAuthorId());
        ratingDTO.setMovieId(ratingParam.getMovieId());
        ratingDTO.setRating(ratingParam.getRating());
        ratingCacheList.add(ratingDTO);
        return true;
    }

    @Override
    public double getAverageRatingByMovieId(int movieId) {
        double sumRating = 0;
        int count = 0;
        for(RatingDTO ratingDTO:ratingCacheList){
            if(movieId == ratingDTO.getMovieId()){
                sumRating+= ratingDTO.getRating();
                count++;
            }
        }
        if(count!=0){
            return sumRating/count;
        }
        return 0;
    }
}
