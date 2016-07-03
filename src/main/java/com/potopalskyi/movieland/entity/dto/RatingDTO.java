package com.potopalskyi.movieland.entity.dto;

public class RatingDTO {
    private int movieId;
    private int userId;
    private double rating;

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "RatingDTO{" +
                "movieId=" + movieId +
                ", userId=" + userId +
                ", rating=" + rating +
                '}';
    }
}
