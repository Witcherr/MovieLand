package com.potopalskyi.movieland.entity.param;

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

    public boolean isCorrectParams() {
        return !(authorId <= 0 || movieId <= 0 || rating > 10 || rating < 1);
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
