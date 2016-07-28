package com.potopalskyi.movieland.util;

import com.potopalskyi.movieland.entity.dto.MovieDTO;
import com.potopalskyi.movieland.entity.dto.MovieDetailedDTO;
import com.potopalskyi.movieland.entity.business.Country;
import com.potopalskyi.movieland.entity.business.Genre;
import com.potopalskyi.movieland.entity.business.Movie;
import com.potopalskyi.movieland.entity.business.Review;
import com.potopalskyi.movieland.entity.dto.MovieListDTO;

import java.util.ArrayList;
import java.util.List;

public class ConverterToDTO {
    public static MovieDTO convertToMovieDTO(Movie movie){
        MovieDTO movieDTO = new MovieDTO();
        List<String> genres = new ArrayList<>();
        movieDTO.setTitleRussian(movie.getTitleRussian());
        movieDTO.setTitleEnglish(movie.getTitleEnglish());
        movieDTO.setYear(movie.getYear());
        movieDTO.setRating(movie.getRating());
        movieDTO.setPrice(movie.getPrice());
        for(Genre genre: movie.getGenreList()){
            genres.add(genre.getName());
        }
        movieDTO.setGenre(genres);
        return movieDTO;
    }

    public static MovieDetailedDTO convertToDetailedMovieDTO(Movie movie){
        MovieDetailedDTO movieDetailedDTO = new MovieDetailedDTO();
        List<String> tempList = new ArrayList<>();
        movieDetailedDTO.setTitleRussian(movie.getTitleRussian());
        movieDetailedDTO.setTitleEnglish(movie.getTitleEnglish());
        movieDetailedDTO.setYear(movie.getYear());
        movieDetailedDTO.setDescription(movie.getDescription());
        movieDetailedDTO.setRating(movie.getRating());
        for(Country country: movie.getCountryList()){
            tempList.add(country.getName());
        }
        movieDetailedDTO.setCountry(tempList);
        tempList = new ArrayList<>();
        for(Genre genre: movie.getGenreList()){
            tempList.add(genre.getName());
        }
        movieDetailedDTO.setGenre(tempList);
        tempList = new ArrayList<>();
        for(Review review: movie.getReviewList()){
            tempList.add(review.getDescription());
        }
        movieDetailedDTO.setReview(tempList);
        movieDetailedDTO.setPrice(movie.getPrice());
        return movieDetailedDTO;
    }

    public static MovieListDTO convertToMovieListDTO(List<Movie> movies){
        MovieListDTO movieListDTO = new MovieListDTO();
        List<MovieDTO> movieDTOList = new ArrayList<>();
        for (Movie movie : movies) {
            movieDTOList.add(convertToMovieDTO(movie));
        }
        movieListDTO.setMovieDTOList(movieDTOList);
        return movieListDTO;
    }

}
