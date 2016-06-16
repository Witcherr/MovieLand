package com.potopalskyi.movieland.service.impl;

import com.potopalskyi.movieland.dao.ReviewDAO;
import com.potopalskyi.movieland.entity.Review;
import com.potopalskyi.movieland.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    ReviewDAO reviewDAO;

    @Override
    public List<Review> getReviewByMovieId(int id) {
        return reviewDAO.getReviewByMovieId(id);
    }
}
