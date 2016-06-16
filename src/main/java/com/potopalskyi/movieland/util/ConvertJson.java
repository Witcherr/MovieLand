package com.potopalskyi.movieland.util;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.potopalskyi.movieland.controller.dto.ConvertToDTO;
import com.potopalskyi.movieland.controller.dto.MovieDTO;
import com.potopalskyi.movieland.controller.dto.MovieDetailedDTO;
import com.potopalskyi.movieland.entity.Movie;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ConvertJson {

    public JsonElement toJsonElement(Movie movie){
        Gson gson = new Gson();
        MovieDTO movieDTO = ConvertToDTO.convertToMovieDTO(movie);
        return gson.toJsonTree(movieDTO);
    }

    public String toJsonDetailed(Movie movie) {
        Gson gson = new Gson();
        MovieDetailedDTO movieDetailedDTO = ConvertToDTO.convertToDetailedMovieDTO(movie);
        return gson.toJson(movieDetailedDTO);
    }

    public String toJson(List<Movie> movies) {
        Gson gson = new Gson();
        List<JsonElement> list = new ArrayList<>();
        for(Movie movie: movies){
            list.add(toJsonElement(movie));
        }
        return gson.toJson(list);
    }
}
