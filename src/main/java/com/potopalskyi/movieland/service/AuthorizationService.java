package com.potopalskyi.movieland.service;

import com.potopalskyi.movieland.entity.User;
import com.potopalskyi.movieland.entity.UserCredential;
import com.potopalskyi.movieland.entity.annotation.RoleTypeRequired;

public interface AuthorizationService {

    boolean checkUserCredential(UserCredential userCredential, User user);

    String generateToken(User user);

    boolean checkRightsForRequest(String token, RoleTypeRequired roleTypeRequired);
}
