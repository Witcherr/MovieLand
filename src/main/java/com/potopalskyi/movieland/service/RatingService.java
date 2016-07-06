package com.potopalskyi.movieland.service;

import com.potopalskyi.movieland.entity.dto.TotalRatingDTO;
import com.potopalskyi.movieland.entity.param.RatingParam;
import com.potopalskyi.movieland.entity.dto.RatingDTO;

import java.util.List;

public interface RatingService {

    void addRating(RatingParam ratingParam);

    List<RatingDTO> getAllRating();

    double getAverageRatingByMovieId(int movieId);

    TotalRatingDTO getTotalRating(int movieId);
}
