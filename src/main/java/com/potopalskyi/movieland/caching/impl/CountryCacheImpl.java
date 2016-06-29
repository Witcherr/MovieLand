package com.potopalskyi.movieland.caching.impl;

import com.potopalskyi.movieland.caching.CountryCache;
import com.potopalskyi.movieland.entity.Country;
import com.potopalskyi.movieland.entity.dto.CountryCacheDTO;
import com.potopalskyi.movieland.service.CountryService;
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
public class CountryCacheImpl implements CountryCache{
    private Logger logger = LoggerFactory.getLogger(CountryCacheImpl.class);

    private List<CountryCacheDTO> countryCacheList = new CopyOnWriteArrayList<>();

    @Autowired
    private MovieService movieService;

    @Autowired
    private CountryService countryService;

    @Override
    public List<Country> getCountryByMovieId(int movieId) {

        List<Country> countries = new ArrayList<>();
        boolean flag = false;
        logger.info("Start getting country from cache");
        for (int i = 0; i < countryCacheList.size(); i++){
            if(movieId == countryCacheList.get(i).getMovieId()){
                countries = countryCacheList.get(i).getCountries();
                flag = true;
                logger.info("Countries for movieId = " + movieId + " was found in cache");
                break;
            }
        }
        if(!flag){
            logger.info("Country for movieId = " + movieId + " was not found in cache. Try to add information to cache from database");
            countries = addNewElementToCache(movieId);
        }
        return countries;
    }

    @Scheduled(fixedRate = 4 * 60 * 60 * 1000)
    @Override
    public void fillCache() {
        logger.info("Start filling of cache for country");
        List<Integer> movieIdList = movieService.getAllMoviesId();
        countryCacheList.clear();
        for (int i = 0; i < movieIdList.size(); i++){
            countryCacheList.add(new CountryCacheDTO());
            countryCacheList.get(i).setMovieId(movieIdList.get(i));
            countryCacheList.get(i).setCountries(countryService.getCountryById(countryCacheList.get(i).getMovieId()));
        }
        logger.info("Enf filling of cache for country");
    }

    @Override
    public List<Country> addNewElementToCache(int movieId) {
        countryCacheList.add(new CountryCacheDTO());
        countryCacheList.get(countryCacheList.size() - 1).setMovieId(movieId);
        List<Country> countries = countryService.getCountryById(movieId);
        countryCacheList.get(countryCacheList.size() - 1).setCountries(countries);
        if (countries != null){
            logger.info("Country was got from database and added to cache");
        }
        return countries;
    }
}
