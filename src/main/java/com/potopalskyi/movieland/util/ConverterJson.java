package com.potopalskyi.movieland.util;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.potopalskyi.movieland.entity.business.Movie;
import com.potopalskyi.movieland.entity.dto.MovieDTO;
import com.potopalskyi.movieland.entity.dto.MovieDetailedDTO;
import com.potopalskyi.movieland.entity.param.MovieSearchParam;
import com.potopalskyi.movieland.entity.param.RatingParam;
import com.potopalskyi.movieland.entity.param.ReviewAlterParam;
import com.potopalskyi.movieland.entity.param.UserCredentialParam;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ConverterJson {
    private Gson gson = new Gson();

    private JsonElement toJsonElement(Movie movie){
        MovieDTO movieDTO = ConverterToDTO.convertToMovieDTO(movie);
        return gson.toJsonTree(movieDTO);
    }

    public String toJsonDetailed(Movie movie) {
        MovieDetailedDTO movieDetailedDTO = ConverterToDTO.convertToDetailedMovieDTO(movie);
        return gson.toJson(movieDetailedDTO);
    }

    public String toJson(List<Movie> movies) {
        List<JsonElement> list = new ArrayList<>();
        for(Movie movie: movies){
            list.add(toJsonElement(movie));
        }
        return gson.toJson(list);
    }

    public MovieSearchParam toMovieSearchParam(String json){
        return gson.fromJson(json, MovieSearchParam.class);
    }

    public ReviewAlterParam toReviewAlterParam(String json){
        return gson.fromJson(json, ReviewAlterParam.class);
    }

    public UserCredentialParam toUserCredential(String json){
        return gson.fromJson(json, UserCredentialParam.class);
    }

    public RatingParam toRatingParam(String json){
        return gson.fromJson(json, RatingParam.class);
    }
}
