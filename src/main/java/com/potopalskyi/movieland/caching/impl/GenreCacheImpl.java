package com.potopalskyi.movieland.caching.impl;

import com.potopalskyi.movieland.caching.GenreCache;
import com.potopalskyi.movieland.entity.Genre;
import com.potopalskyi.movieland.entity.dto.GenreCacheDTO;
import com.potopalskyi.movieland.service.GenreService;
import com.potopalskyi.movieland.service.MovieService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
public class GenreCacheImpl implements GenreCache {
    private Logger logger = LoggerFactory.getLogger(GenreCacheImpl.class);

    @Autowired
    private GenreService genreService;

    @Autowired
    private MovieService movieService;

    private List<GenreCacheDTO> genreCacheList = new CopyOnWriteArrayList<>();

    @Override
    public List<Genre> getGenreById(int movieId) {
        boolean flag = false;
        List<Genre> genre = new ArrayList<>();
        for (int i = 0; i < genreCacheList.size(); i++) {
            if (movieId == genreCacheList.get(i).getMovieId()) {
                genre = genreCacheList.get(i).getGenre();
                flag = true;
                break;
            }
        }
        //if (!flag) {
        //    genre =
        //}
        //movie.setGenreList(genreService.getGenreById(movie.getId()))
        return genre;
    }

    @Scheduled(fixedRate = 4 * 60 * 60 * 1000)
    @Override
    public void fillCache() {
        logger.debug("Start filling of cache");
        List<Integer> movieIdList = movieService.getAllMoviesId();
        for (int i = 0; i < movieIdList.size(); i++) {
            genreCacheList.add(new GenreCacheDTO());
            genreCacheList.get(i).setMovieId(movieIdList.get(i));
        }
        for (int i = 0; i < genreCacheList.size(); i++) {
            genreCacheList.get(i).setGenre(genreService.getGenreById(genreCacheList.get(i).getMovieId()));
        }
        logger.debug("End filling of cache");
    }

    @Override
    public void updateCache(int movieId) {

    }
}
