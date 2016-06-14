package com.potopalskyi.movieland.entity;

import java.util.List;

public class Movie {
    private int id;
    private String titleRussian;
    private String titleEnglish;
    private int year;
    private double rating;
    private String description;
    private List<Genre> genreList;
    private List<Review> reviewList;
    private List<Country> countryList;

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

    public List<Genre> getGenreList() {
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

    public void setGenreList(List<Genre> genreList) {
        this.genreList = genreList;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Review> getReviewList() {
        return reviewList;
    }

    public void setReviewList(List<Review> reviewList) {
        this.reviewList = reviewList;
    }

    public List<Country> getCountryList() {
        return countryList;
    }

    public void setCountryList(List<Country> countryList) {
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
