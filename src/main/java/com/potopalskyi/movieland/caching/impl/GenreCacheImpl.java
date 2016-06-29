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

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private GenreService genreService;

    @Autowired
    private MovieService movieService;

    private List<GenreCacheDTO> genreCacheList = new CopyOnWriteArrayList<>();

    @Override
    public List<Genre> getGenreByMovieId(int movieId) {
        boolean flag = false;
        List<Genre> genre = new ArrayList<>();
        logger.info("Start getting genre from cache");
        for (int i = 0; i < genreCacheList.size(); i++) {
            if (movieId == genreCacheList.get(i).getMovieId()) {
                genre = genreCacheList.get(i).getGenre();
                flag = true;
                logger.info("Genre for movieId = " + movieId + " was got from cache");
                break;
            }
        }
        if (!flag) {
            logger.info("Genre for movieId = " + movieId + " was not found in cache. Try to add information to cache from database");
            genre = addNewElementToCache(movieId);
        }
        return genre;
    }

    @Scheduled(fixedRate = 4 * 60 * 60 * 1000)
    @Override
    public void fillCache() {
        logger.debug("Start filling of cache for genre");
        List<Integer> movieIdList = movieService.getAllMoviesId();
        genreCacheList.clear();
        for (int i = 0; i < movieIdList.size(); i++) {
            genreCacheList.add(new GenreCacheDTO());
            genreCacheList.get(i).setMovieId(movieIdList.get(i));
            genreCacheList.get(i).setGenre(genreService.getGenreById(genreCacheList.get(i).getMovieId()));
        }
        logger.debug("End filling of cache for genre");
    }

    @Override
    public List<Genre> addNewElementToCache(int movieId) {
        genreCacheList.add(new GenreCacheDTO());
        genreCacheList.get(genreCacheList.size() - 1).setMovieId(movieId);
        List<Genre> genres = genreService.getGenreById(movieId);
        genreCacheList.get(genreCacheList.size() - 1).setGenre(genres);
        if (genres != null){
            logger.info("Genre was got from database and added to cache");
        }
        return genres;
    }
}
