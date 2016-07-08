package com.potopalskyi.movieland.caching;

import com.potopalskyi.movieland.entity.param.RatingParam;

public interface RatingCache {
    void fillCache();

    void addNewElement(RatingParam ratingParam);

    double getAverageRatingByMovieId(int movieId);
}
