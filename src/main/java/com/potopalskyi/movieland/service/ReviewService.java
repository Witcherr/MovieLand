package com.potopalskyi.movieland.service;

import com.potopalskyi.movieland.entity.Review;
import com.potopalskyi.movieland.entity.ReviewAlterParam;

import java.util.List;

public interface ReviewService {

    List<Review> getReviewByMovieId(int id);

    boolean addReview(ReviewAlterParam reviewAlterParam);

    boolean deleteReview(ReviewAlterParam reviewAlterParam);
}
