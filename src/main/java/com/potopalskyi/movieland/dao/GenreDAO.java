package com.potopalskyi.movieland.dao;

import com.potopalskyi.movieland.entity.business.Genre;

import java.util.List;

public interface GenreDAO {

    List<Genre> getGenreById(int id);
}
