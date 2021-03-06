package com.potopalskyi.movieland.entity.param;

public class ReviewAlterParam {
    private int authorId;
    private int movieId;
    private String review;

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public boolean isCorrectParams() {
        return !(review == null || review.length() <= 0 || authorId <= 0 || movieId <= 0);
    }

    @Override
    public String toString() {
        return "ReviewAlterParam{" +
                "authorId=" + authorId +
                ", movieId=" + movieId +
                ", review='" + review + '\'' +
                '}';
    }
}
