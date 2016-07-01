package com.potopalskyi.movieland.caching;

public interface UserTokenCache {

    void addNewElementToCache(String login, String token);

    void refreshCache();

    boolean containsToken(String token);
}
