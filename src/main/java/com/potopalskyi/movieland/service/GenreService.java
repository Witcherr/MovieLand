package com.potopalskyi.movieland.service;

import com.potopalskyi.movieland.entity.Genre;

import java.util.List;

public interface GenreService {
    public List<Genre> getGenreById(int id);
}