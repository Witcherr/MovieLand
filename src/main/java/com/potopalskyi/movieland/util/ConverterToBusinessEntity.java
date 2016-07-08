package com.potopalskyi.movieland.util;

import com.potopalskyi.movieland.entity.business.Country;
import com.potopalskyi.movieland.entity.business.Genre;
import com.potopalskyi.movieland.entity.business.Movie;
import com.potopalskyi.movieland.entity.param.MovieNewParam;

import java.util.ArrayList;
import java.util.List;

public class ConverterToBusinessEntity {
    public static Movie convertToMovie(MovieNewParam movieNewParam) {
        Movie movie = new Movie();
        List<Genre> genreList = new ArrayList<>();
        List<Country> countryList = new ArrayList<>();
        movie.setTitleRussian(movieNewParam.getTitleRussian());
        movie.setTitleEnglish(movieNewParam.getTitleEnglish());
        movie.setYear(movieNewParam.getYear());
        movie.setPrice(movieNewParam.getPrice());
        movie.setDescription(movieNewParam.getDescription());
        for (String genre : movieNewParam.getGenreList()) {
            Genre genreTemp = new Genre();
            genreTemp.setName(genre);
            genreList.add(genreTemp);
        }
        movie.setGenreList(genreList);
        for (String country : movieNewParam.getCountryList()) {
            Country countryTemp = new Country();
            countryTemp.setName(country);
            countryList.add(countryTemp);
        }
        movie.setCountryList(countryList);
        return movie;
    }
}
