package com.potopalskyi.movieland.service.impl;

import com.potopalskyi.movieland.dao.MovieDAO;
import com.potopalskyi.movieland.entity.business.Movie;
import com.potopalskyi.movieland.entity.param.MovieSearchParam;
import com.potopalskyi.movieland.entity.param.MovieSortAndLimitParam;
import com.potopalskyi.movieland.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {

    private static final String MINUS_ONE_DOUBLE = "-1.0";
    private static final String EMPTY = "Empty";

    @Autowired
    private MovieDAO movieDAO;

    @Autowired
    private CountryService countryService;

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private GenreService genreService;

    @Autowired
    private RatingService ratingService;

    @Override
    public List<Movie> getAllMovies(MovieSortAndLimitParam movieSortAndLimitParam) {
        List<Movie> movies = movieDAO.getAllMovies(movieSortAndLimitParam);
        for (Movie movie : movies) {
            movie.setGenreList(genreService.getGenreFromCacheByMovieId(movie.getId()));
            movie.setRating(ratingService.getAverageRatingByMovieId(movie.getId()));
        }
        return movies;
    }

    @Override
    public List<Movie> getMoviesBySearch(MovieSearchParam movieSearchParam) {
        List<Movie> movies = movieDAO.getMoviesBySearch(movieSearchParam);
        for (Movie movie : movies) {
            movie.setGenreList(genreService.getGenreFromCacheByMovieId(movie.getId()));
            movie.setRating(ratingService.getAverageRatingByMovieId(movie.getId()));
        }
        return movies;
    }

    @Override
    public Movie getMovieById(int id) {
        Movie movie = movieDAO.getMovieById(id);
        movie.setGenreList(genreService.getGenreFromCacheByMovieId(movie.getId()));
        movie.setCountryList(countryService.getCountryFromCacheByMovieId(movie.getId()));
        movie.setRating(ratingService.getAverageRatingByMovieId(id));
        movie.setReviewList(reviewService.getTwoReviewByMovieId(id));
        return movie;
    }

    @Override
    public List<Integer> getAllMoviesId() {
        return movieDAO.getAllMoviesId();
    }

    @Override
    public void updateUserRating(Movie movie, int userId) {
        String userRating = String.valueOf(ratingService.getUserRating(userId, movie.getId()));
        if(MINUS_ONE_DOUBLE.equals(userRating)){
            userRating = EMPTY;
        }
        movie.setUserRating(userRating);
    }
}
