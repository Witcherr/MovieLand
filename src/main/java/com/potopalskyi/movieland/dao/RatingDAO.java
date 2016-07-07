package com.potopalskyi.movieland.dao;

import com.potopalskyi.movieland.entity.dto.TotalRatingDTO;
import com.potopalskyi.movieland.entity.param.RatingParam;

public interface RatingDAO {

    void addRating(RatingParam ratingParam);

    TotalRatingDTO getTotalRating(int movieId);

    double getUserRating(int userId, int movieId);
}
