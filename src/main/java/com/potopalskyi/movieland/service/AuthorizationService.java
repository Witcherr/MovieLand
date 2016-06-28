package com.potopalskyi.movieland.service;

import com.potopalskyi.movieland.entity.UserCredential;

public interface AuthorizationService {

    boolean checkUserCredential(UserCredential userCredential);

    String generateToken(UserCredential userCredential);
}
