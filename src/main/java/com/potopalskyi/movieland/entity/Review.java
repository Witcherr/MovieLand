package com.potopalskyi.movieland.entity;

public class Review {
    private User user;
    private Movie movie;
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    @Override
    public String toString() {
        return "Review{" +
                "user=" + user +
                ", movie=" + movie +
                ", description='" + description + '\'' +
                '}';
    }
}
