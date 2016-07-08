package com.potopalskyi.movieland.security;

import com.potopalskyi.movieland.entity.business.User;
import com.potopalskyi.movieland.security.entity.UserTokenDTO;

public interface UserTokenCache {

    void addNewElementToCache(User user, String token);

    void refreshCache();

    UserTokenDTO getUserTokenDTO(String token);

    int getUserIdIfExist(String token);
}
