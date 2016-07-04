package com.potopalskyi.movieland.caching;

import com.potopalskyi.movieland.entity.business.User;
import com.potopalskyi.movieland.entity.dto.UserTokenDTO;

public interface UserTokenCache {

    void addNewElementToCache(User user, String token);

    void refreshCache();

    UserTokenDTO getUserTokenDTO(String token);
}
