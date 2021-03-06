package com.potopalskyi.movieland.security;

import com.potopalskyi.movieland.entity.business.User;
import com.potopalskyi.movieland.security.entity.UserTokenDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
public class UserTokenCacheImpl implements UserTokenCache {

    private static final int MAX_USER_SESSION_HOURS = 2;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private List<UserTokenDTO> userTokenCacheList = new CopyOnWriteArrayList<>();

    @Override
    public void addNewElementToCache(User user, String token) {
        logger.debug("Start adding token into user cache for user = {} ", user.getName());
        UserTokenDTO userTokenDTO = new UserTokenDTO();
        userTokenDTO.setLogin(user.getName());
        userTokenDTO.setToken(token);
        userTokenDTO.setConnectionTime(LocalDateTime.now());
        userTokenDTO.setRoleType(user.getRoleType());
        userTokenDTO.setUserId(user.getId());
        userTokenCacheList.add(userTokenDTO);
        logger.debug("End adding token into cache for user = {} ", user.getName());
    }

    @Scheduled(fixedRate = 60 * 1000)
    @Override
    public void refreshCache() {
        logger.debug("Start refreshing user cache");
        LocalDateTime currentTime = LocalDateTime.now();
        for(int i = 0; i< userTokenCacheList.size(); i++){
            if(currentTime.isAfter(userTokenCacheList.get(i).getConnectionTime().plusHours(MAX_USER_SESSION_HOURS))){
                logger.debug("Removing from user cache user = {}", userTokenCacheList.get(i).getLogin());
                userTokenCacheList.remove(i);
            }
        }
    }

    @Override
    public UserTokenDTO getUserTokenDTO(String token) {
        for(UserTokenDTO userTokenDTO: userTokenCacheList){
            if (token.equals(userTokenDTO.getToken())){
                return userTokenDTO;
            }
        }
        return null;
    }

    @Override
    public int getUserIdIfExist(String token) {
        for(UserTokenDTO userTokenDTO: userTokenCacheList){
            if(token.equals(userTokenDTO.getToken())){
                return userTokenDTO.getUserId();
            }
        }
        return -1;
    }
}
