package com.potopalskyi.movieland.service.impl;

import com.potopalskyi.movieland.dao.ReviewDAO;
import com.potopalskyi.movieland.entity.business.Review;
import com.potopalskyi.movieland.entity.param.ReviewAlterParam;
import com.potopalskyi.movieland.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewDAO reviewDAO;

    @Override
    public List<Review> getReviewByMovieId(int id) {
        return reviewDAO.getReviewByMovieId(id);
    }

    @Override
    public boolean addReview(ReviewAlterParam reviewAlterParam) {
        return reviewDAO.addReview(reviewAlterParam);
    }

    @Override
    public boolean deleteReview(ReviewAlterParam reviewAlterParam) {
        return reviewDAO.deleteReview(reviewAlterParam);
    }
}
