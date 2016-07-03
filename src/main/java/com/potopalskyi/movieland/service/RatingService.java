package com.potopalskyi.movieland.service;

import com.potopalskyi.movieland.entity.RatingParam;
import com.potopalskyi.movieland.entity.dto.RatingDTO;

import java.util.List;

public interface RatingService {

    boolean addRating(RatingParam ratingParam);

    List<RatingDTO> getAllRating();

    double getAverageRatingByMovieId(int movieId);
}
