package com.potopalskyi.movieland.service;

import com.potopalskyi.movieland.entity.business.Review;
import com.potopalskyi.movieland.entity.param.ReviewAlterParam;

import java.util.List;

public interface ReviewService {

    List<Review> getReviewByMovieId(int movieId);

    List<Review> getTwoReviewByMovieId(int movieId);

    void addReview(ReviewAlterParam reviewAlterParam);

    void deleteReview(ReviewAlterParam reviewAlterParam);

    void deleteReview(int movieId);
}
