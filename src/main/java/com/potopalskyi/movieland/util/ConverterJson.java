package com.potopalskyi.movieland.util;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.potopalskyi.movieland.entity.*;
import com.potopalskyi.movieland.entity.dto.MovieDTO;
import com.potopalskyi.movieland.entity.dto.MovieDetailedDTO;
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

    public ReviewAlterParam toReviewAddParam(String json){
        return gson.fromJson(json, ReviewAlterParam.class);
    }

    public UserCredential toUserCredential(String json){
        return gson.fromJson(json, UserCredential.class);
    }
}
