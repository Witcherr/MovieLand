package com.potopalskyi.movieland.entity;

public class RatingParam {
    private int authorId;
    private int movieId;
    private double rating;

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    @Override
    public String toString() {
        return "RatingParam{" +
                "authorId=" + authorId +
                ", movieId=" + movieId +
                ", rating=" + rating +
                '}';
    }
}
