package com.potopalskyi.movieland.service.impl;

import com.potopalskyi.movieland.dao.MovieDAO;
import com.potopalskyi.movieland.entity.Movie;
import com.potopalskyi.movieland.entity.MovieSearchParam;
import com.potopalskyi.movieland.entity.MovieSortParam;
import com.potopalskyi.movieland.entity.Review;
import com.potopalskyi.movieland.service.CountryService;
import com.potopalskyi.movieland.service.GenreService;
import com.potopalskyi.movieland.service.MovieService;
import com.potopalskyi.movieland.service.ReviewService;
import com.potopalskyi.movieland.util.MovieComparator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    MovieDAO movieDAO;

    @Autowired
    GenreService genreService;

    @Autowired
    CountryService countryService;

    @Autowired
    ReviewService reviewService;

    @Override
    public List<Movie> getAllMovies(MovieSortParam movieSortParam) {
        List<Movie> movies = movieDAO.getAllMovies();
        if(movies.size()!= 0) {
            Collections.sort(movies, new MovieComparator(movieSortParam));
            for (Movie movie : movies) {
                movie.setGenreList(genreService.getGenreById(movie.getId()));
            }
        }
        return movies;
    }

    @Override
    public List<Movie> getMoviesBySearch(MovieSearchParam movieSearchParam) {
        List<Movie> movies = movieDAO.getMoviesBySearch(movieSearchParam);
        if (movies.size() != 0) {
            for (Movie movie : movies) {
                movie.setGenreList(genreService.getGenreById(movie.getId()));
            }
        }
        return movies;
    }

    @Override
    public Movie getMovieById(int id) {
        Movie movie = movieDAO.getMovieById(id);
        if (!movie.isEmpty()) {
            movie.setGenreList(genreService.getGenreById(id));
            movie.setCountryList(countryService.getCountryById(id));
            List<Review> reviews = reviewService.getReviewByMovieId(id);
            if (reviews.size() >= 2) {
                movie.setReviewList(reviews.subList(0, 2));
            } else if (reviews.size() == 1) {
                movie.setReviewList(reviews);
            }
        }
        return movie;
    }
}
