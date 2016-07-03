package com.potopalskyi.movieland.caching;

import com.potopalskyi.movieland.entity.RatingParam;

public interface RatingCache {
    void fillCache();

    boolean addNewElement(RatingParam ratingParam);

    double getAverageRatingByMovieId(int movieId);
}
