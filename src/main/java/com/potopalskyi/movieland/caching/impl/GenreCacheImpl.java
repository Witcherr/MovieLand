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

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
public class GenreCacheImpl implements GenreCache {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private GenreService genreService;

    @Autowired
    private MovieService movieService;

    private List<GenreCacheDTO> genreCacheList = new CopyOnWriteArrayList<>();

    @Override
    public List<Genre> getGenreByMovieId(int movieId) {
        logger.info("Start getting genre from cache");
        for (int i = 0; i < genreCacheList.size(); i++) {
            if (movieId == genreCacheList.get(i).getMovieId()) {
                return genreCacheList.get(i).getGenre();
            }
        }
        logger.info("Genre for movieId = " + movieId + " was not found in cache. Try to add information to cache from database");
        return addNewElementToCache(movieId);
    }

    @Scheduled(fixedRate = 4 * 60 * 60 * 1000)
    @Override
    public void fillCache() {
        logger.debug("Start filling of cache for genre");
        List<Integer> movieIdList = movieService.getAllMoviesId();
        genreCacheList.clear();
        for (int i = 0; i < movieIdList.size(); i++) {
            int movieId = movieIdList.get(i);
            GenreCacheDTO genreCacheDTO = new GenreCacheDTO();
            genreCacheDTO.setMovieId(movieId);
            genreCacheDTO.setGenre(genreService.getGenreById(movieId));
            genreCacheList.add(genreCacheDTO);
        }
        logger.debug("End filling of cache for genre");
    }

    @Override
    public List<Genre> addNewElementToCache(int movieId) {
        GenreCacheDTO genreCacheDTO = new GenreCacheDTO();
        genreCacheDTO.setMovieId(movieId);
        List<Genre> genres = genreService.getGenreById(movieId);
        genreCacheDTO.setGenre(genres);
        if (genres != null) {
            logger.info("Genre was got from database and added to cache");
        }
        return genres;
    }
}
