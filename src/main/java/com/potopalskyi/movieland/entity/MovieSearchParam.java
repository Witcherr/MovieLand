package com.potopalskyi.movieland.entity;

public class MovieSearchParam {
    private String titleRussian;
    private String titleEnglish;
    private String genre;
    private String year;
    private String country;

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

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "MovieSearchParam{" +
                "titleRussian='" + titleRussian + '\'' +
                ", titleEnglish='" + titleEnglish + '\'' +
                ", genre='" + genre + '\'' +
                ", year=" + year +
                ", country='" + country + '\'' +
                '}';
    }
}
