package com.potopalskyi.movieland.dao;

import com.potopalskyi.movieland.entity.param.RatingParam;
import com.potopalskyi.movieland.entity.dto.RatingDTO;

import java.util.List;

public interface RatingDAO {

    boolean addRating(RatingParam ratingParam);

    List<RatingDTO> getAllRating();
}
