package com.potopalskyi.movieland.controller;

import com.potopalskyi.movieland.entity.Movie;
import com.potopalskyi.movieland.entity.MovieSearchParam;
import com.potopalskyi.movieland.service.MovieService;
import com.potopalskyi.movieland.util.ConvertJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/v1")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @Autowired
    private ConvertJson convertJson;

    @RequestMapping(value = "/movies", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String getAllMovies(@RequestParam(value = "rating", required = false) String ratingOrder, @RequestParam(value = "price", required = false) String priceOrder) {
        List<Movie> movies = movieService.getAllMovies(ratingOrder, priceOrder);
        return convertJson.toJson(movies);
    }

    @RequestMapping(value = "/movie/{movieId}", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String getMovieById(@PathVariable("movieId") int movieId) {
        Movie movie = movieService.getMovieById(movieId);
        return convertJson.toJsonDetailed(movie);
    }

    @RequestMapping(value = "/search", produces = "text/plain;charset=UTF-8", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> getMoviesBySearch(@RequestBody String json) {
        MovieSearchParam movieSearchParam = convertJson.toMovieSearchParam(json);
        List<Movie> movies = movieService.getMoviesBySearch(movieSearchParam);
        if (movies == null) {
            return new ResponseEntity<>("There were not found films", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(convertJson.toJson(movies), HttpStatus.OK);
    }
}
