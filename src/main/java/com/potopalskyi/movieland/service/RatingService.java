package com.potopalskyi.movieland.service;

import com.potopalskyi.movieland.entity.dto.TotalRatingDTO;
import com.potopalskyi.movieland.entity.param.RatingParam;
import com.potopalskyi.movieland.entity.dto.RatingDTO;

import java.util.List;

public interface RatingService {

    void addRating(RatingParam ratingParam);

    void addRatingToDAO(RatingParam ratingParam);

    double getAverageRatingByMovieId(int movieId);

    TotalRatingDTO getTotalRating(int movieId);
}
