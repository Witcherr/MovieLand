package com.potopalskyi.movieland.dao;

import com.potopalskyi.movieland.entity.business.Review;
import com.potopalskyi.movieland.entity.param.ReviewAlterParam;

import java.util.List;

public interface ReviewDAO {

    List<Review> getReviewByMovieId(int id);

    boolean addReview(ReviewAlterParam reviewAlterParam);

    boolean deleteReview(ReviewAlterParam reviewAlterParam);
}
