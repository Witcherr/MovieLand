package com.potopalskyi.movieland.util;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.potopalskyi.movieland.entity.Movie;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ConvertJson {

    public JsonObject toJsonObject(Movie movie){
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("titleRussian", movie.getTitleRussian());
        jsonObject.addProperty("tittleEnglish", movie.getTitleEnglish());
        jsonObject.addProperty("year", movie.getYear());
        jsonObject.addProperty("rating", movie.getRating());
        jsonObject.addProperty("genre", /*String.valueOf(movie.getGenreList()*/ movie.getGenreList().toString());
        return jsonObject;
    }

    public String toJsonDetailed(Movie movie) {
        Gson gson = new Gson();
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("titleRussian", movie.getTitleRussian());
        jsonObject.addProperty("tittleEnglish", movie.getTitleEnglish());
        jsonObject.addProperty("year", movie.getYear());
        jsonObject.addProperty("country",  String.valueOf(movie.getCountryList()));
        jsonObject.addProperty("genre", String.valueOf(movie.getGenreList()));
        jsonObject.addProperty("description", movie.getDescription());
        jsonObject.addProperty("reviews", String.valueOf(movie.getReviewList()));
        return gson.toJson(jsonObject);
    }

    public String toJson(List<Movie> movies) {
        Gson gson = new Gson();
        List<JsonObject> list = new ArrayList<>();
        for(Movie movie: movies){
            list.add(toJsonObject(movie));
        }
        return gson.toJson(list);
    }
}
