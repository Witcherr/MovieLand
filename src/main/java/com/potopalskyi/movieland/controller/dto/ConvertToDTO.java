package com.potopalskyi.movieland.controller.dto;

import com.potopalskyi.movieland.entity.Genre;
import com.potopalskyi.movieland.entity.Movie;

import java.util.ArrayList;
import java.util.List;

public class ConvertToDTO {
    public static MovieDTO convertMovieToDTO(Movie movie){
        MovieDTO movieDTO = new MovieDTO();
        movieDTO.setTitleRussian(movie.getTitleRussian());
        movieDTO.setTitleEnglish(movie.getTitleEnglish());
        movieDTO.setYear(movie.getYear());
        movieDTO.setRating(movie.getRating());
        List<String> genres = new ArrayList<>();
        for(Genre genre: movie.getGenreList()){
            genres.add(genre.getName());
            //movieDTO.getGenreList().add(genre.getName());
        }
        movieDTO.setGenre(genres);
        return movieDTO;
    }
}
