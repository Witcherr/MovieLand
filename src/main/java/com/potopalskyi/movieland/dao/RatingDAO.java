package com.potopalskyi.movieland.dao;

import com.potopalskyi.movieland.entity.dto.TotalRatingDTO;
import com.potopalskyi.movieland.entity.param.RatingParam;
import com.potopalskyi.movieland.entity.dto.RatingDTO;

import java.util.List;

public interface RatingDAO {

    void addRating(RatingParam ratingParam);

    TotalRatingDTO getTotalRating(int movieId);
}
