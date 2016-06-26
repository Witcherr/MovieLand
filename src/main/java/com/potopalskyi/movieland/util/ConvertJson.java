package com.potopalskyi.movieland.util;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.potopalskyi.movieland.entity.dto.MovieDTO;
import com.potopalskyi.movieland.entity.dto.MovieDetailedDTO;
import com.potopalskyi.movieland.entity.Movie;
import com.potopalskyi.movieland.entity.MovieSearchParam;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ConvertJson {
    private Gson gson = new Gson();

    private JsonElement toJsonElement(Movie movie){
        MovieDTO movieDTO = ConvertToDTO.convertToMovieDTO(movie);
        return gson.toJsonTree(movieDTO);
    }

    public String toJsonDetailed(Movie movie) {
        MovieDetailedDTO movieDetailedDTO = ConvertToDTO.convertToDetailedMovieDTO(movie);
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
}
