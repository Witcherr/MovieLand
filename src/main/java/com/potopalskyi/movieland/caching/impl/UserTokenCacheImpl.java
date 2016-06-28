package com.potopalskyi.movieland.caching.impl;

import com.potopalskyi.movieland.caching.UserTokenCache;
import com.potopalskyi.movieland.entity.UserCredential;
import com.potopalskyi.movieland.entity.dto.UserTokenDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
public class UserTokenCacheImpl implements UserTokenCache {

    private Logger logger = LoggerFactory.getLogger(UserTokenCacheImpl.class);

    private List<UserTokenDTO> userTokenCacheList = new CopyOnWriteArrayList<>();

    @Override
    public void addNewElementToCache(UserCredential userCredential, String token) {
        logger.debug("Start adding token into user cache for user : " + userCredential.getName());
        UserTokenDTO userTokenDTO = new UserTokenDTO();
        userTokenDTO.setUserCredential(userCredential);
        userTokenDTO.setToken(token);
        userTokenDTO.setConnectionTime(LocalDateTime.now());
        userTokenCacheList.add(userTokenDTO);
        logger.debug("End adding token into cache for user : " + userCredential.getName());
    }

    @Scheduled(fixedRate = 60 * 1000)
    @Override
    public void refreshCache() {
        logger.debug("Start refreshing user cache");
        LocalDateTime currentTime = LocalDateTime.now();
        for(int i = 0; i< userTokenCacheList.size(); i++){
            if(currentTime.isAfter(userTokenCacheList.get(i).getConnectionTime().plusHours(2))){
                logger.debug("Removing from user cache user :" + userTokenCacheList.get(i).getUserCredential());
                userTokenCacheList.remove(i);
            }
        }
    }
}
