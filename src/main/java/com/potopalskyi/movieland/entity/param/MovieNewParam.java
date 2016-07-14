package com.potopalskyi.movieland.entity.param;

import java.util.List;

public class MovieNewParam {
    private String titleRussian;
    private String titleEnglish;
    private int year;
    private double price;
    private String description;
    private List<String> genre;
    private List<String> country;

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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getGenreList() {
        return genre;
    }

    public void setGenreList(List<String> genre) {
        this.genre = genre;
    }

    public List<String> getCountryList() {
        return country;
    }

    public void setCountryList(List<String> country) {
        this.country = country;
    }

    public boolean isCorrectParams() {
        //return !(authorId <= 0 || movieId <= 0 || rating > 10 || rating < 1);
        return !(titleRussian == null || titleEnglish == null || year <= 0 || price <= 0 || description == null
                || genre == null || country == null);
    }

    @Override
    public String toString() {
        return "MovieNewParam{" +
                "titleRussian='" + titleRussian + '\'' +
                ", titleEnglish='" + titleEnglish + '\'' +
                ", year=" + year +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", genre=" + genre +
                ", country=" + country +
                '}';
    }
}
