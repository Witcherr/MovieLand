package com.potopalskyi.movieland.security;

import com.potopalskyi.movieland.entity.business.User;
import com.potopalskyi.movieland.security.entity.RoleTypeRequired;
import com.potopalskyi.movieland.security.entity.UserCredentialParam;

public interface SecurityService {

    boolean checkUserCredential(UserCredentialParam userCredentialParam, User user);

    String generateToken(User user);

    boolean checkRightsForRequest(String token, RoleTypeRequired roleTypeRequired);

    boolean checkAlterPermission(String token, int userId);
}
