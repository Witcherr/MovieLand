package com.potopalskyi.movieland.caching;

import com.potopalskyi.movieland.entity.UserCredential;

public interface UserTokenCache {

    void addNewElementToCache(UserCredential userCredential, String token);

    void refreshCache();
}
