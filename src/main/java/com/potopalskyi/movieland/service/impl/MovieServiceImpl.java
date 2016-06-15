package com.potopalskyi.movieland.service.impl;

import com.potopalskyi.movieland.dao.MovieDAO;
import com.potopalskyi.movieland.entity.Genre;
import com.potopalskyi.movieland.entity.Movie;
import com.potopalskyi.movieland.service.GenreService;
import com.potopalskyi.movieland.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    MovieDAO movieDAO;

    @Autowired
    GenreService genreService;

    @Override
    public List<Movie> getAllMovies() {
        List<Movie> movies = movieDAO.getAllMovies();
        for(Movie movie: movies){
            movie.setGenreList(genreService.getByMovie(movie));
        }
        return movies;
    }

    @Override
    public Movie getMovieById(int id) {
        Movie movie = movieDAO.getMovieById(id);
        List<String> reviews = movieDAO.getReviewById(id);
        if (reviews.size()>= 2){
            //movie.setReviewList(reviews.subList(0, 2));
        } else if (reviews.size() == 1){
            //movie.setReviewList(reviews);
        }
        return movie;
    }
}
