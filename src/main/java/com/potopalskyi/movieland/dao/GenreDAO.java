package com.potopalskyi.movieland.dao;

import com.potopalskyi.movieland.entity.Genre;
import com.potopalskyi.movieland.entity.Movie;

import java.util.List;

public interface GenreDAO {

    List<Genre> getGenreById(int id);
}
