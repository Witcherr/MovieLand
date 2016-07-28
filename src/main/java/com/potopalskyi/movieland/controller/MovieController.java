package com.potopalskyi.movieland.controller;

import com.potopalskyi.movieland.entity.business.Movie;
import com.potopalskyi.movieland.entity.dto.MovieDetailedDTO;
import com.potopalskyi.movieland.entity.param.MovieNewParam;
import com.potopalskyi.movieland.entity.param.MovieSearchParam;
import com.potopalskyi.movieland.entity.param.MovieSortLimitCurrencyParam;
import com.potopalskyi.movieland.security.entity.RoleTypeRequired;
import com.potopalskyi.movieland.entity.enums.RoleType;
import com.potopalskyi.movieland.entity.exception.NoDataFoundException;
import com.potopalskyi.movieland.service.MovieService;
import com.potopalskyi.movieland.util.ConverterJson;
import com.potopalskyi.movieland.util.ConverterToBusinessEntity;
import com.potopalskyi.movieland.util.ConverterToDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

    @RequestMapping(value = "/movies",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public ResponseEntity<?> getAllMovies(@RequestParam(value = "rating", required = false) String ratingSortType,
                                          @RequestParam(value = "price", required = false) String priceSortType,
                                          @RequestParam(value = "page", defaultValue = "1") String page,
                                          @RequestParam(value = "currency", required = false) String currencyType) {
        logger.info("Start process of getting all movies with Rating order = {}, Price rating = {}, Page = {}", ratingSortType, priceSortType, page);
        long startTime = System.currentTimeMillis();
        List<Movie> movies;
        MovieSortLimitCurrencyParam movieSortLimitCurrencyParam = new MovieSortLimitCurrencyParam(ratingSortType, priceSortType, page, currencyType);
        try {
            movies = movieService.getAllMovies(movieSortLimitCurrencyParam);
        } catch (NoDataFoundException e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        logger.info("End of getting all movies with Rating order = {}, Price rating = {}, Page = {}. It took {} ms", ratingSortType, priceSortType, page, System.currentTimeMillis() - startTime);
        return new ResponseEntity<>(ConverterToDTO.convertToMovieListDTO(movies), HttpStatus.OK);
    }

    @RequestMapping(value = "/movie/{movieId}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public ResponseEntity<?> getMovieById(@PathVariable("movieId") int movieId, HttpServletRequest request,
                                          @RequestParam(value = "currency", required = false) String currency) {
        logger.info("Start process of getting movie with id = {}", movieId);
        long startTime = System.currentTimeMillis();
        MovieDetailedDTO movieDetailedDTO;
        try {
            movieDetailedDTO = movieService.getMovieById(movieId);
            String token = request.getHeader("token");
            movieService.setUserRatingForMovie(movieDetailedDTO, token, movieId);
            movieService.setCurrency(movieDetailedDTO, currency);
        } catch (NoDataFoundException e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        logger.info("Movie with id {} received, it took {} ms", movieId, System.currentTimeMillis() - startTime);
        return new ResponseEntity<>(movieDetailedDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/movies/search", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> getMoviesBySearch(@RequestBody String json) {
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
        return new ResponseEntity<>(ConverterToDTO.convertToMovieListDTO(movies), HttpStatus.OK);
    }

    @RoleTypeRequired(role = RoleType.ADMIN)
    @RequestMapping(value = "/movie", method = RequestMethod.POST)
    public ResponseEntity<String> addMovie(@RequestBody String json) {
        logger.info("Start process of adding movie = {}", json);
        long startTime = System.currentTimeMillis();
        MovieNewParam movieNewParam = converterJson.toMovieNewParam(json);
        if (!movieNewParam.isCorrectParams()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Movie movie = ConverterToBusinessEntity.convertToMovie(movieNewParam);
        try {
            movieService.addNewMovie(movie);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        logger.info("End process of adding movie = {}. It took {} ms", json, System.currentTimeMillis() - startTime);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RoleTypeRequired(role = RoleType.USER)
    @RequestMapping(value = "/movie", method = RequestMethod.PUT)
    public ResponseEntity<String> editMovie(@RequestBody String json) {
        logger.info("Start process of editing movie = {}", json);
        long startTime = System.currentTimeMillis();
        MovieNewParam movieNewParam = converterJson.toMovieNewParam(json);
        if (!movieNewParam.isCorrectParams()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Movie movie = ConverterToBusinessEntity.convertToMovie(movieNewParam);
        try {
            movieService.updateMovie(movie);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        logger.info("End process of editing movie = {}. It took {} ms", json, System.currentTimeMillis() - startTime);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RoleTypeRequired(role = RoleType.ADMIN)
    @RequestMapping(value = "/movie/{movieId}", method = RequestMethod.DELETE)
    public ResponseEntity<String> markMovie(@PathVariable("movieId") int movieId) {
        logger.info("Start process of marking movieId = {}", movieId);
        long startTime = System.currentTimeMillis();
        try {
            movieService.markMovie(movieId);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        logger.info("End process of marking movieId = {}. It took {} ms", movieId, System.currentTimeMillis() - startTime);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RoleTypeRequired(role = RoleType.ADMIN)
    @RequestMapping(value = "/movie/{movieId}/unmark", method = RequestMethod.POST)
    public ResponseEntity<String> unMarkMovie(@PathVariable("movieId") int movieId) {
        logger.info("Start process of unmarking movieId = {}", movieId);
        long startTime = System.currentTimeMillis();
        try {
            movieService.unMarkMovie(movieId);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        logger.info("End process of unmarking movieId = {}. It took {} ms", movieId, System.currentTimeMillis() - startTime);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/poster/{movieId}", produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    public ResponseEntity<byte[]> getMoviePoster(@PathVariable("movieId") int movieId) {
        logger.info("Start process of getting poster for movieId = {}", movieId);
        long startTime = System.currentTimeMillis();
        byte[] poster;
        try {
            poster = movieService.getMoviePoster(movieId);
        } catch (NoDataFoundException e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        logger.info("End process of getting poster for movieId = {}. It took {} ms", movieId, System.currentTimeMillis() - startTime);
        return new ResponseEntity<>(poster, HttpStatus.OK);
    }
}
