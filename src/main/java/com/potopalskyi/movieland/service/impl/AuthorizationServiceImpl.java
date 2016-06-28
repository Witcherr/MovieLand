package com.potopalskyi.movieland.service.impl;

import com.potopalskyi.movieland.caching.UserTokenCache;
import com.potopalskyi.movieland.entity.User;
import com.potopalskyi.movieland.entity.UserCredential;
import com.potopalskyi.movieland.service.AuthorizationService;
import com.potopalskyi.movieland.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AuthorizationServiceImpl implements AuthorizationService{

    @Autowired
    private UserService userService;

    @Autowired
    private UserTokenCache userTokenCache;

    @Override
    public boolean checkUserCredential(UserCredential userCredential) {
        User user = userService.getUserByName(userCredential.getName());
        if(user != null && user.getPassword().equals(userCredential.getPassword())) {
                return true;
        }
        return false;
    }

    @Override
    public String generateToken(UserCredential userCredential) {
        String token = UUID.randomUUID().toString();
        userTokenCache.addNewElementToCache(userCredential, token);
        return token;
    }
}
