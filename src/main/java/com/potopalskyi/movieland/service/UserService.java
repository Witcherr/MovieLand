package com.potopalskyi.movieland.service;

import com.potopalskyi.movieland.entity.business.User;

public interface UserService {
    User getUserByName(String name);
}
