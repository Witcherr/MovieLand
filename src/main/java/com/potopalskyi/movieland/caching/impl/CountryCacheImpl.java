package com.potopalskyi.movieland.caching.impl;

import com.potopalskyi.movieland.caching.CountryCache;
import com.potopalskyi.movieland.entity.business.Country;
import com.potopalskyi.movieland.entity.dto.CountryCacheDTO;
import com.potopalskyi.movieland.service.CountryService;
import com.potopalskyi.movieland.service.MovieService;
import com.potopalskyi.movieland.util.Util;
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
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private List<CountryCacheDTO> countryCacheList = new CopyOnWriteArrayList<>();

    @Autowired
    private MovieService movieService;

    @Autowired
    private CountryService countryService;

    @Override
    public List<Country> getCountryByMovieId(int movieId) {
        logger.info("Start getting country from cache");
        for(CountryCacheDTO countryCacheDTO: countryCacheList){
            if(movieId == countryCacheDTO.getMovieId()){
                return Util.cloneListCountry(countryCacheDTO.getCountries());
            }
        }
        logger.info("Country for movieId = {} was not found in cache. Try to add information to cache from database", movieId);
        return Util.cloneListCountry(addNewElementToCache(movieId));
    }

    @Scheduled(fixedRate = 4 * 60 * 60 * 1000)
    @Override
    public void fillCache() {
        logger.info("Start filling of cache for country");
        List<Integer> movieIdList = movieService.getAllMoviesId();
        List<CountryCacheDTO> tempList = new ArrayList<>();
        for (int i = 0; i < movieIdList.size(); i++){
            int movieId = movieIdList.get(i);
            CountryCacheDTO countryCacheDTO = new CountryCacheDTO();
            countryCacheDTO.setMovieId(movieId);
            countryCacheDTO.setCountries(countryService.getCountryById(movieId));
            tempList.add(countryCacheDTO);
        }
        countryCacheList = new CopyOnWriteArrayList<>(tempList);
        logger.info("End filling of cache for country");
    }

    @Override
    public List<Country> addNewElementToCache(int movieId) {
        CountryCacheDTO countryCacheDTO = new CountryCacheDTO();
        countryCacheDTO.setMovieId(movieId);
        List<Country> countries = countryService.getCountryById(movieId);
        countryCacheDTO.setCountries(countries);
        if(countries != null){
            countryCacheList.add(countryCacheDTO);
            logger.info("Country for movieId = {} was got from database and added to cache", movieId);
        }
        return countries;
    }
}
