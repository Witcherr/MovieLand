package com.potopalskyi.movieland.service;

import com.potopalskyi.movieland.entity.Review;

import java.util.List;

public interface ReviewService {

    public List<Review> getReviewByMovieId(int id);
}
