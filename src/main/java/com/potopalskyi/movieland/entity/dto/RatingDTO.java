package com.potopalskyi.movieland.entity.dto;

public class RatingDTO {
    private int movieId;
    private double sumRating;
    private int countRating;

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public double getSumRating() {
        return sumRating;
    }

    public void setSumRating(double sumRating) {
        this.sumRating = sumRating;
    }

    public int getCountRating() {
        return countRating;
    }

    public void setCountRating(int countRating) {
        this.countRating = countRating;
    }

    @Override
    public String toString() {
        return "RatingDTO{" +
                "movieId=" + movieId +
                ", sumRating=" + sumRating +
                ", countRating=" + countRating +
                '}';
    }
}
