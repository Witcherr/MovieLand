package com.potopalskyi.movieland.service.impl;

import com.potopalskyi.movieland.dao.ReviewDAO;
import com.potopalskyi.movieland.entity.business.Review;
import com.potopalskyi.movieland.entity.param.ReviewAlterParam;
import com.potopalskyi.movieland.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewDAO reviewDAO;

    @Override
    public List<Review> getReviewByMovieId(int movieId) {
        List<Review> listReviews = reviewDAO.getReviewByMovieId(movieId);
        if (listReviews != null) {
            return listReviews;
        }
        Review review = new Review();
        review.setDescription("No reviews");
        listReviews = new ArrayList<>();
        listReviews.add(review);
        return listReviews;
    }

    @Override
    public List<Review> getTwoReviewByMovieId(int movieId) {
        List<Review> listReview = getReviewByMovieId(movieId);
        if (listReview.size() >= 2) {
            return listReview.subList(0, 2);
        } else
            return listReview;
    }

    @Override
    public void addReview(ReviewAlterParam reviewAlterParam) {
        reviewDAO.addReview(reviewAlterParam);
    }

    @Override
    public void deleteReview(ReviewAlterParam reviewAlterParam) {
        reviewDAO.deleteReview(reviewAlterParam);
    }
}
