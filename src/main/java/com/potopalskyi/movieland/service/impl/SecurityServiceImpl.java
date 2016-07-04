package com.potopalskyi.movieland.service.impl;

import com.potopalskyi.movieland.caching.UserTokenCache;
import com.potopalskyi.movieland.entity.param.UserCredentialParam;
import com.potopalskyi.movieland.entity.business.User;
import com.potopalskyi.movieland.entity.annotation.RoleTypeRequired;
import com.potopalskyi.movieland.entity.dto.UserTokenDTO;
import com.potopalskyi.movieland.entity.enums.RoleType;
import com.potopalskyi.movieland.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SecurityServiceImpl implements SecurityService {

    @Autowired
    private UserTokenCache userTokenCache;

    @Override
    public boolean checkUserCredential(UserCredentialParam userCredentialParam, User user) {
        return user.getPassword().equals(userCredentialParam.getPassword());
    }

    @Override
    public String generateToken(User user) {
        String token = UUID.randomUUID().toString();
        userTokenCache.addNewElementToCache(user, token);
        return token;
    }

    @Override
    public boolean checkRightsForRequest(String token, RoleTypeRequired roleTypeRequired) {
        UserTokenDTO userTokenDTO = userTokenCache.getUserTokenDTO(token);
        return userTokenDTO != null && userTokenDTO.getRoleType().equalOrHigher(roleTypeRequired.role());
    }

    @Override
    public boolean checkUserForAltering(String token, int userId) {
        UserTokenDTO userTokenDTO = userTokenCache.getUserTokenDTO(token);
        return userId == userTokenDTO.getUserId() || RoleType.ADMIN == userTokenDTO.getRoleType();
    }
}
