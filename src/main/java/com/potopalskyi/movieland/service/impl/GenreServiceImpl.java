package com.potopalskyi.movieland.service.impl;

import com.potopalskyi.movieland.dao.GenreDAO;
import com.potopalskyi.movieland.entity.Genre;
import com.potopalskyi.movieland.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreServiceImpl implements GenreService{

    @Autowired
    private GenreDAO genreDAO;

    @Override
    public List<Genre> getGenreById(int id) {
        return genreDAO.getGenreById(id);
    }
}
