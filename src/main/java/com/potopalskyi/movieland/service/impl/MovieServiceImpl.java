package com.potopalskyi.movieland.service.impl;

import com.potopalskyi.movieland.caching.CountryCache;
import com.potopalskyi.movieland.caching.GenreCache;
import com.potopalskyi.movieland.dao.MovieDAO;
import com.potopalskyi.movieland.entity.business.Movie;
import com.potopalskyi.movieland.entity.param.MovieSearchParam;
import com.potopalskyi.movieland.entity.param.MovieSortAndLimitParam;
import com.potopalskyi.movieland.entity.business.Review;
import com.potopalskyi.movieland.service.MovieService;
import com.potopalskyi.movieland.service.RatingService;
import com.potopalskyi.movieland.service.ReviewService;
//import org.apache.commons.lang3.Validate;
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
        for (Movie movie : movies) {
            movie.setGenreList(genreCache.getGenreByMovieId(movie.getId()));
            movie.setRating(ratingService.getAverageRatingByMovieId(movie.getId()));
        }
        return movies;
    }

    @Override
    public List<Movie> getMoviesBySearch(MovieSearchParam movieSearchParam) {
        List<Movie> movies = movieDAO.getMoviesBySearch(movieSearchParam);
        for (Movie movie : movies) {
            movie.setGenreList(genreCache.getGenreByMovieId(movie.getId()));
            movie.setRating(ratingService.getAverageRatingByMovieId(movie.getId()));
        }
        return movies;
    }

    @Override
    public Movie getMovieById(int id) {
        Movie movie = movieDAO.getMovieById(id);
        movie.setGenreList(genreCache.getGenreByMovieId(id));
        movie.setCountryList(countryCache.getCountryByMovieId(id));
        movie.setRating(ratingService.getAverageRatingByMovieId(id));
        movie.setReviewList(reviewService.getTwoReviewByMovieId(id));
        return movie;
    }

    @Override
    public List<Integer> getAllMoviesId() {
        return movieDAO.getAllMoviesId();
    }
}
