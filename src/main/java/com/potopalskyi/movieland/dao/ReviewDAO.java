package com.potopalskyi.movieland.dao;

import com.potopalskyi.movieland.entity.Review;

import java.util.List;

public interface ReviewDAO {

    public List<Review> getReviewByMovieId(int id);
}
