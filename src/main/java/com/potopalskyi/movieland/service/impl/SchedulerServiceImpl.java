package com.potopalskyi.movieland.service.impl;

import com.potopalskyi.movieland.caching.CountryCache;
import com.potopalskyi.movieland.caching.GenreCache;
import com.potopalskyi.movieland.security.UserTokenCache;
import com.potopalskyi.movieland.service.CurrencyService;
import com.potopalskyi.movieland.service.MovieService;
import com.potopalskyi.movieland.service.SchedulerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class SchedulerServiceImpl implements SchedulerService{

    @Autowired
    private MovieService movieService;

    @Autowired
    private GenreCache genreCache;

    @Autowired
    private CountryCache countryCache;

    @Autowired
    private UserTokenCache userTokenCache;

    @Autowired
    private CurrencyService currencyService;

    @Scheduled(cron = "0 0 0 * * *")
    @Override
    public void deleteMarkedMovies() {
        movieService.deleteMarkedMovies();
    }

    @Scheduled(fixedRate = 4 * 60 * 60 * 1000)
    @Override
    public void fillCountryCache() {
        countryCache.fillCache();
    }

    @Scheduled(fixedRate = 4 * 60 * 60 * 1000)
    @Override
    public void fillGenreCache() {
        genreCache.fillCache();
    }

    @Scheduled(fixedRate = 60 * 1000)
    @Override
    public void refreshUserTokenCache() {
        userTokenCache.refreshCache();
    }

    @Scheduled(cron="0 1 13 * * *", zone="Europe/Helsinki")
    @Override
    public void refreshCurrencyCache() {
        currencyService.fillRateList();
    }
}
