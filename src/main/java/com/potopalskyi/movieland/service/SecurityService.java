package com.potopalskyi.movieland.service;

import com.potopalskyi.movieland.entity.business.User;
import com.potopalskyi.movieland.entity.param.UserCredentialParam;
import com.potopalskyi.movieland.entity.annotation.RoleTypeRequired;

public interface SecurityService {

    boolean checkUserCredential(UserCredentialParam userCredentialParam, User user);

    String generateToken(User user);

    boolean checkRightsForRequest(String token, RoleTypeRequired roleTypeRequired);

    boolean checkUserForAltering(String token, int userId);
}
