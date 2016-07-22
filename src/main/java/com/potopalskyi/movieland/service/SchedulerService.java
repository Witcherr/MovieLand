package com.potopalskyi.movieland.service;

public interface SchedulerService {

    void deleteMarkedMovies();

    void fillCountryCache();

    void fillGenreCache();

    void refreshUserTokenCache();

    void refreshCurrencyCache();
}
