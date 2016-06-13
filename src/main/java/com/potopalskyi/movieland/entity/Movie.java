package com.potopalskyi.movieland.entity;

import java.util.List;

public class Movie {
    private int id;
    private String titleRussian;
    private String tittleEnglish;
    private int year;
    private double rating;
    private String description;
    private List<String> genre;
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

    public String getTittleEnglish() {
        return tittleEnglish;
    }

    public int getYear() {
        return year;
    }

    public double getRating() {
        return rating;
    }

    public List<String> getGenre() {
        return genre;
    }

    public void setTitleRussian(String titleRussian) {
        this.titleRussian = titleRussian;
    }

    public void setTittleEnglish(String tittleEnglish) {
        this.tittleEnglish = tittleEnglish;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public void setGenre(List<String> genre) {
        this.genre = genre;
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
                ", tittleEnglish='" + tittleEnglish + '\'' +
                ", year=" + year +
                ", rating=" + rating +
                ", description='" + description + '\'' +
                ", genre=" + genre +
                ", reviewList=" + reviewList +
                ", countryList=" + countryList +
                '}';
    }
}
