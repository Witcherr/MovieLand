package com.potopalskyi.movieland.dao;

import com.potopalskyi.movieland.entity.dto.RatingDTO;
import com.potopalskyi.movieland.entity.param.RatingParam;

import java.util.List;

public interface RatingDAO {

    void addRating(RatingParam ratingParam);

    double getUserRating(int userId, int movieId);

    List<RatingDTO> getTotalRatingsForAllMovies();
}
