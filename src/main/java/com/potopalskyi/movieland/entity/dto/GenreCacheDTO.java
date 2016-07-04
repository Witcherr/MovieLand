package com.potopalskyi.movieland.entity.dto;

import com.potopalskyi.movieland.entity.business.Genre;

import java.util.List;

public class GenreCacheDTO {

    private int movieId;
    private List<Genre> genre;

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public List<Genre> getGenre() {
        return genre;
    }

    public void setGenre(List<Genre> genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "GenreCacheDTO{" +
                "movieId=" + movieId +
                ", genre=" + genre +
                '}';
    }
}
