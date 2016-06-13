package com.potopalskyi.movieland.entity;

import java.util.List;

public class Movie {
    private int id;
    private String titleRussian;
    private String titleEnglish;
    private int year;
    private double rating;
    private String description;
    private List<String> genreList;
    private List<String> reviewList;
    private List<String> countryList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitleRussian() {
        return titleRussian;
    }

    public String getTitleEnglish() {
        return titleEnglish;
    }

    public int getYear() {
        return year;
    }

    public double getRating() {
        return rating;
    }

    public List<String> getGenreList() {
        return genreList;
    }

    public void setTitleRussian(String titleRussian) {
        this.titleRussian = titleRussian;
    }

    public void setTitleEnglish(String titleEnglish) {
        this.titleEnglish = titleEnglish;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public void setGenreList(List<String> genreList) {
        this.genreList = genreList;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getReviewList() {
        return reviewList;
    }

    public void setReviewList(List<String> reviewList) {
        this.reviewList = reviewList;
    }

    public List<String> getCountryList() {
        return countryList;
    }

    public void setCountryList(List<String> countryList) {
        this.countryList = countryList;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", titleRussian='" + titleRussian + '\'' +
                ", titleEnglish='" + titleEnglish + '\'' +
                ", year=" + year +
                ", rating=" + rating +
                ", description='" + description + '\'' +
                ", genreList=" + genreList +
                ", reviewList=" + reviewList +
                ", countryList=" + countryList +
                '}';
    }
}
