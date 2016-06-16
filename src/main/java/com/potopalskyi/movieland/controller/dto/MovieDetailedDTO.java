package com.potopalskyi.movieland.controller.dto;

import java.util.List;

public class MovieDetailedDTO {
    private String titleRussian;
    private String titleEnglish;
    private int year;
    private List<String> country;
    private List<String> genre;
    private String description;
    private List<String> review;
    private double rating;

    public String getTitleRussian() {
        return titleRussian;
    }

    public void setTitleRussian(String titleRussian) {
        this.titleRussian = titleRussian;
    }

    public String getTitleEnglish() {
        return titleEnglish;
    }

    public void setTitleEnglish(String titleEnglish) {
        this.titleEnglish = titleEnglish;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getGenre() {
        return genre;
    }

    public void setGenre(List<String> genre) {
        this.genre = genre;
    }

    public List<String> getReview() {
        return review;
    }

    public void setReview(List<String> review) {
        this.review = review;
    }

    public List<String> getCountry() {
        return country;
    }

    public void setCountry(List<String> country) {
        this.country = country;
    }
}
