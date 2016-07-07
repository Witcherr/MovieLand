package com.potopalskyi.movieland.service;

import com.potopalskyi.movieland.entity.dto.TotalRatingDTO;
import com.potopalskyi.movieland.entity.param.RatingParam;

public interface RatingService {

    void addRating(RatingParam ratingParam);

    void addRatingToDAO(RatingParam ratingParam);

    double getAverageRatingByMovieId(int movieId);

    TotalRatingDTO getTotalRating(int movieId);

    double getUserRating(int userId, int movieId);
}
