package com.potopalskyi.movieland.security;

import com.potopalskyi.movieland.entity.business.User;
import com.potopalskyi.movieland.entity.enums.RoleType;
import com.potopalskyi.movieland.security.entity.RoleTypeRequired;
import com.potopalskyi.movieland.security.entity.UserCredentialParam;
import com.potopalskyi.movieland.security.entity.UserTokenDTO;
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
    public boolean checkAlterPermission(String token, int userId) {
        UserTokenDTO userTokenDTO = userTokenCache.getUserTokenDTO(token);
        return userId == userTokenDTO.getUserId() || RoleType.ADMIN == userTokenDTO.getRoleType();
    }

    @Override
    public int getUserIdIfExist(String token) {
        return userTokenCache.getUserIdIfExist(token);
    }
}
