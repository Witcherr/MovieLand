package com.potopalskyi.movieland.util;

import com.google.gson.Gson;
import com.potopalskyi.movieland.entity.param.MovieNewParam;
import com.potopalskyi.movieland.entity.param.MovieSearchParam;
import com.potopalskyi.movieland.entity.param.RatingParam;
import com.potopalskyi.movieland.entity.param.ReviewAlterParam;
import com.potopalskyi.movieland.security.entity.UserCredentialParam;
import org.springframework.stereotype.Service;

@Service
public class ConverterJson {
    private Gson gson = new Gson();

    public MovieSearchParam toMovieSearchParam(String json){
        return gson.fromJson(json, MovieSearchParam.class);
    }

    public MovieNewParam toMovieNewParam(String json){
        return gson.fromJson(json, MovieNewParam.class);
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
