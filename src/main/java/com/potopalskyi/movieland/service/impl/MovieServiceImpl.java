package com.potopalskyi.movieland.service.impl;

import com.potopalskyi.movieland.dao.MovieDAO;
import com.potopalskyi.movieland.entity.business.Genre;
import com.potopalskyi.movieland.entity.business.Movie;
import com.potopalskyi.movieland.entity.dto.MovieDetailedDTO;
import com.potopalskyi.movieland.entity.param.MovieSearchParam;
import com.potopalskyi.movieland.entity.param.MovieSortAndLimitParam;
import com.potopalskyi.movieland.security.SecurityService;
import com.potopalskyi.movieland.service.*;
import com.potopalskyi.movieland.util.ConverterToDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {

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

    @Autowired
    private SecurityService securityService;

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
    public MovieDetailedDTO getMovieById(int id) {
        Movie movie = movieDAO.getMovieById(id);
        movie.setGenreList(genreService.getGenreFromCacheByMovieId(movie.getId()));
        movie.setCountryList(countryService.getCountryFromCacheByMovieId(movie.getId()));
        movie.setRating(ratingService.getAverageRatingByMovieId(id));
        movie.setReviewList(reviewService.getTwoReviewByMovieId(id));
        return ConverterToDTO.convertToDetailedMovieDTO(movie);
    }

    @Override
    public List<Integer> getAllMoviesId() {
        return movieDAO.getAllMoviesId();
    }

    @Override
    public void setUserRatingForMovie(MovieDetailedDTO movieDetailedDTO, String token, int movieid) {
        int userId = securityService.getUserIdIfExist(token);
        if(userId != 0){
            double userRating = ratingService.getUserRating(userId, movieid);
            if(userRating != -1){
                movieDetailedDTO.setUserRating(userRating);
            }
        }
    }

    @Override
    public void addNewMovie(Movie movie) {
        movieDAO.saveNewMovie(movie);


        for(Genre genre: movie.getGenreList()){
            genre.setId(genreService.getGenreIdByName(genre.getName()));
        }

    }
}
