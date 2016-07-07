package com.potopalskyi.movieland.controller;

import com.potopalskyi.movieland.entity.business.Movie;
import com.potopalskyi.movieland.entity.param.MovieSearchParam;
import com.potopalskyi.movieland.entity.param.MovieSortAndLimitParam;
import com.potopalskyi.movieland.security.SecurityService;
import com.potopalskyi.movieland.security.entity.RoleTypeRequired;
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

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/v1")
public class MovieController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private MovieService movieService;

    @Autowired
    private ConverterJson converterJson;

    @Autowired
    private SecurityService securityService;

    @RequestMapping(value = "/movies", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ResponseEntity<String> getAllMovies(@RequestParam(value = "rating", required = false) String ratingSortType,
                                               @RequestParam(value = "price", required = false) String priceSortType,
                                               @RequestParam(value = "page", defaultValue = "1") String page) {
        logger.info("Start process of getting all movies with Rating order = {}, Price rating = {}, Page = {}", ratingSortType, priceSortType, page);
        long startTime = System.currentTimeMillis();
        List<Movie> movies;
        MovieSortAndLimitParam movieSortAndLimitParam = new MovieSortAndLimitParam(ratingSortType, priceSortType, page);
        try {
            movies = movieService.getAllMovies(movieSortAndLimitParam);
        } catch (NoDataFoundException e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        logger.info("End of getting all movies with Rating order = {}, Price rating = {}, Page = {}. It took {} ms", ratingSortType, priceSortType, page, System.currentTimeMillis() - startTime);
        return new ResponseEntity<>(converterJson.toJson(movies), HttpStatus.OK);
    }

    @RequestMapping(value = "/movie/{movieId}", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ResponseEntity<String> getMovieById(@PathVariable("movieId") int movieId, HttpServletRequest request) {
        logger.info("Start process of getting movie with id = {}", movieId);
        long startTime = System.currentTimeMillis();
        Movie movie;
        try {
            movie = movieService.getMovieById(movieId);
            String token = request.getHeader("token");
            if(token!=null){
                int userId = securityService.getUserIdIfExist(token);
                if(userId != -1){
                    movieService.updateUserRating(movie, userId);
                }
            }
        } catch (NoDataFoundException e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        logger.info("Movie with id {} received, it took {} ms", movieId, System.currentTimeMillis() - startTime);
        return new ResponseEntity<>(converterJson.toJsonDetailed(movie), HttpStatus.OK);
    }

    @RequestMapping(value = "/movies/search", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> getMoviesBySearch(@RequestBody String json) {
        logger.info("Start process of getting movies with search params {}", json);
        long startTime = System.currentTimeMillis();
        MovieSearchParam movieSearchParam = converterJson.toMovieSearchParam(json);
        List<Movie> movies;
        try {
            movies = movieService.getMoviesBySearch(movieSearchParam);
        } catch (NoDataFoundException e) {
            logger.warn("There are no movies with params = {}", json);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        logger.info("End process of getting movies with search param = {}. It took {} ms", json, System.currentTimeMillis() - startTime);
        return new ResponseEntity<>(converterJson.toJson(movies), HttpStatus.OK);
    }

    @RoleTypeRequired(role = RoleType.USER)
    @RequestMapping(value = "/movie", method = RequestMethod.POST)
    @ResponseBody
    public void addMovie() {

    }

    @RoleTypeRequired(role = RoleType.USER)
    @RequestMapping(value = "/movie", method = RequestMethod.PUT)
    @ResponseBody
    public void editMovie() {

    }

    @RoleTypeRequired(role = RoleType.ADMIN)
    @RequestMapping(value = "/movie/{movieId}", method = RequestMethod.DELETE)
    @ResponseBody
    public void markMovie() {

    }

    @RoleTypeRequired(role = RoleType.ADMIN)
    @RequestMapping(value = "/movie/{movieId}", method = RequestMethod.POST)
    @ResponseBody
    public void unMarkMovie() {

    }
}
