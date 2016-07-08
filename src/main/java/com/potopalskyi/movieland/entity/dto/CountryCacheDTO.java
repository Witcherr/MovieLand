package com.potopalskyi.movieland.entity.dto;

import com.potopalskyi.movieland.entity.business.Country;

import java.util.List;

public class CountryCacheDTO {
    private int movieId;
    private List<Country> countries;

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public List<Country> getCountries() {
        return countries;
    }

    public void setCountries(List<Country> countries) {
        this.countries = countries;
    }

    @Override
    public String toString() {
        return "CountryCacheDTO{" +
                "movieId=" + movieId +
                ", countries=" + countries +
                '}';
    }
}
