package com.potopalskyi.movieland.service.impl;

import com.potopalskyi.movieland.dao.MovieDAO;
import com.potopalskyi.movieland.entity.business.Movie;
import com.potopalskyi.movieland.entity.dto.MovieDetailedDTO;
import com.potopalskyi.movieland.entity.exception.NoDataFoundException;
import com.potopalskyi.movieland.entity.param.MovieSearchParam;
import com.potopalskyi.movieland.entity.param.MovieSortLimitCurrencyParam;
import com.potopalskyi.movieland.security.SecurityService;
import com.potopalskyi.movieland.service.*;
import com.potopalskyi.movieland.util.ConverterToDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Service
public class MovieServiceImpl implements MovieService {

    private Logger logger = LoggerFactory.getLogger(getClass());

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

    @Autowired
    private CurrencyService currencyService;

    private List<Integer> movieMarkList = new ArrayList<>();
    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private Lock writeLock = readWriteLock.writeLock();

    @Override
    public List<Movie> getAllMovies(MovieSortLimitCurrencyParam movieSortLimitCurrencyParam) {
        List<Movie> movies = movieDAO.getAllMovies(movieSortLimitCurrencyParam);
        for (Movie movie : movies) {
            movie.setGenreList(genreService.getGenreFromCacheByMovieId(movie.getId()));
            movie.setRating(ratingService.getAverageRatingByMovieId(movie.getId()));
            movie.setPrice(currencyService.calculatePriceByCurrencyType(movie.getPrice(), movieSortLimitCurrencyParam.getCurrencyType()));
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
        if (userId != -1) {
            double userRating = ratingService.getUserRating(userId, movieid);
            if (userRating != -1) {
                movieDetailedDTO.setUserRating(userRating);
            }
        }
    }

    @Transactional
    @Override
    public void addNewMovie(Movie movie) {
        movieDAO.saveNewMovie(movie);
        genreService.saveGenreForNewMovie(movie);
        countryService.saveCountryForNewMovie(movie);
    }

    @Transactional
    @Override
    public void updateMovie(Movie movie) {
        movieDAO.updateMovie(movie);
        genreService.updateGenreForMovie(movie);
        countryService.updateCountryForMovie(movie);
    }

    @Override
    public void markMovie(int movieId) {
        writeLock.lock();
        try {
            if (!movieMarkList.contains(movieId)) {
                if (movieDAO.checkExist(movieId)) {
                    movieMarkList.add(movieId);
                } else {
                    throw new NoDataFoundException("MovieId = " + movieId + " doesn't exist!");
                }
            }
        } finally {
            writeLock.unlock();
        }
    }

    @Override
    public void unMarkMovie(int movieId) {
        writeLock.lock();
        try {
            if (movieMarkList.contains(movieId)) {
                movieMarkList.remove(Integer.valueOf(movieId));
            } else {
                throw new NoDataFoundException("MovieId = " + movieId + " is not present in list of marked movies");
            }
        } finally {
            writeLock.unlock();
        }
    }

    @Transactional
    public void deleteMarkedMovies() {
        logger.info("Start deleting marked movies");
        writeLock.lock();
        try {
            for (Integer movieId : movieMarkList) {
                countryService.deleteCountry(movieId);
                genreService.deleteGenre(movieId);
                reviewService.deleteReview(movieId);
                ratingService.deleteRatings(movieId);
                movieDAO.deleteMovie(movieId);
            }
            movieMarkList.clear();
        }catch (RuntimeException e){
            logger.warn("Exception while deleting movieMarkList", e);
        }
        finally {
            writeLock.unlock();
            logger.info("End deleting marked movies");
        }
    }

    @Override
    public byte[] getMoviePoster(int movieId) {
        return  movieDAO.getMoviePoster(movieId);
    }

    @Override
    public void setCurrency(MovieDetailedDTO movieDetailedDTO, String currency) {
        movieDetailedDTO.setPrice(currencyService.calculatePriceByCurrencyType(movieDetailedDTO.getPrice(), currency));
    }
}
