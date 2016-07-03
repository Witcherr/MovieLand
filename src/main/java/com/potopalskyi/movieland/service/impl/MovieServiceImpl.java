package com.potopalskyi.movieland.service.impl;

import com.potopalskyi.movieland.caching.CountryCache;
import com.potopalskyi.movieland.caching.GenreCache;
import com.potopalskyi.movieland.dao.MovieDAO;
import com.potopalskyi.movieland.entity.Movie;
import com.potopalskyi.movieland.entity.MovieSearchParam;
import com.potopalskyi.movieland.entity.MovieSortAndLimitParam;
import com.potopalskyi.movieland.entity.Review;
import com.potopalskyi.movieland.service.MovieService;
import com.potopalskyi.movieland.service.RatingService;
import com.potopalskyi.movieland.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieDAO movieDAO;

    @Autowired
    private CountryCache countryCache;

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private RatingService ratingService;

    @Autowired
    private GenreCache genreCache;

    @Override
    public List<Movie> getAllMovies(MovieSortAndLimitParam movieSortAndLimitParam) {
        List<Movie> movies = movieDAO.getAllMovies(movieSortAndLimitParam);
        if(movies != null) {
            for (Movie movie : movies) {
                movie.setGenreList(genreCache.getGenreByMovieId(movie.getId()));
                movie.setRating(ratingService.getAverageRatingByMovieId(movie.getId()));
            }
        }
        return movies;
    }

    @Override
    public List<Movie> getMoviesBySearch(MovieSearchParam movieSearchParam) {
        List<Movie> movies = movieDAO.getMoviesBySearch(movieSearchParam);
        if (movies != null) {
            for (Movie movie : movies) {
                movie.setGenreList(genreCache.getGenreByMovieId(movie.getId()));
                movie.setRating(ratingService.getAverageRatingByMovieId(movie.getId()));
            }
        }
        return movies;
    }

    @Override
    public Movie getMovieById(int id) {
        Movie movie = movieDAO.getMovieById(id);
        if (movie != null) {
            movie.setGenreList(genreCache.getGenreByMovieId(movie.getId()));
            movie.setCountryList(countryCache.getCountryByMovieId(id));
            movie.setRating(ratingService.getAverageRatingByMovieId(movie.getId()));
            List<Review> reviews = reviewService.getReviewByMovieId(id);
            if (reviews.size() >= 2) {
                movie.setReviewList(reviews.subList(0, 2));
            } else if (reviews.size() == 1) {
                movie.setReviewList(reviews);
            }
        }
        return movie;
    }

    @Override
    public List<Integer> getAllMoviesId() {
        return movieDAO.getAllMoviesId();
    }
}
