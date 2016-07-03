package com.potopalskyi.movieland.controller;

import com.potopalskyi.movieland.entity.Movie;
import com.potopalskyi.movieland.entity.MovieSearchParam;
import com.potopalskyi.movieland.entity.MovieSortAndLimitParam;
import com.potopalskyi.movieland.entity.annotation.RoleTypeRequired;
import com.potopalskyi.movieland.entity.enums.RoleType;
import com.potopalskyi.movieland.entity.exception.NoDataFoundException;
import com.potopalskyi.movieland.service.MovieService;
import com.potopalskyi.movieland.util.ConverterJson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/v1")
public class MovieController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private MovieService movieService;

    @Autowired
    private ConverterJson converterJson;

    @RequestMapping(value = "/movies", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ResponseEntity<String> getAllMovies(@RequestParam(value = "rating", required = false) String ratingSortType,
                                               @RequestParam(value = "price", required = false) String priceSortType,
                                               @RequestParam(value = "page", defaultValue = "1" ) String page) {
        logger.info("Start process of getting all movies");
        MovieSortAndLimitParam movieSortAndLimitParam = new MovieSortAndLimitParam(ratingSortType, priceSortType, page);
        List<Movie> movies = movieService.getAllMovies(movieSortAndLimitParam);
        return new ResponseEntity<>(converterJson.toJson(movies), HttpStatus.OK);
    }

    @RequestMapping(value = "/movie/{movieId}", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ResponseEntity<String> getMovieById(@PathVariable("movieId") int movieId) {
        logger.info("Start process of getting movie with id = " + movieId);
        Movie movie;
        try {
            movie = movieService.getMovieById(movieId);
        } catch (NoDataFoundException e) {
            logger.warn("The movie with id = " + movieId + " wasn't found");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(converterJson.toJsonDetailed(movie), HttpStatus.OK);
    }

    @RequestMapping(value = "/movies/search", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> getMoviesBySearch(@RequestBody String json) {
        logger.info("Start process of getting movies with search params " + json);
        MovieSearchParam movieSearchParam = converterJson.toMovieSearchParam(json);
        List<Movie> movies;
        try {
            movies = movieService.getMoviesBySearch(movieSearchParam);
        } catch (NoDataFoundException e) {
            logger.warn("There are no movies with params " + json);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(converterJson.toJson(movies), HttpStatus.OK);
    }

    @RoleTypeRequired(role = RoleType.USER)
    @RequestMapping( value = "/movie", method = RequestMethod.POST)
    @ResponseBody
    public void addMovie(){

    }

    @RoleTypeRequired(role = RoleType.USER)
    @RequestMapping( value = "/movie", method = RequestMethod.PUT)
    @ResponseBody
    public void editMovie(){

    }

    @RoleTypeRequired(role = RoleType.ADMIN)
    @RequestMapping( value = "/movie/{movieId}", method = RequestMethod.DELETE)
    @ResponseBody
    public void markMovie(){

    }

    @RoleTypeRequired(role = RoleType.ADMIN)
    @RequestMapping( value = "/movie/{movieId}", method = RequestMethod.POST)
    @ResponseBody
    public void unMarkMovie(){

    }
}
