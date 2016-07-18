package com.potopalskyi.movieland.service;

import com.potopalskyi.movieland.entity.dto.RatingDTO;
import com.potopalskyi.movieland.entity.param.RatingParam;

import java.util.List;

public interface RatingService {

    void addRating(RatingParam ratingParam);

    double getAverageRatingByMovieId(int movieId);

    double getUserRating(int userId, int movieId);

    List<RatingDTO> getTotalRatingsForAllMovies();

    void deleteRatings(int movieId);
}
