package com.potopalskyi.movieland.controller;

import com.potopalskyi.movieland.entity.Movie;
import com.potopalskyi.movieland.service.MovieService;
import com.potopalskyi.movieland.util.ConvertJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/v1")
public class MovieLandController {

    @Autowired
    private MovieService movieLandService;

    @Autowired
    private ConvertJson convertJson;

    @RequestMapping(value ="/movies", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String getAllMovies(){
        List<Movie> movies = movieLandService.getAllMovies();
        return convertJson.toJson(movies);
    }

    @RequestMapping(value = "/movie/{movieId}", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String getMovieById(@PathVariable("movieId") int movieId){
        Movie movie = movieLandService.getMovieById(movieId);
        return convertJson.toJsonDetailed(movie);
    }
}
