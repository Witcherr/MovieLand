package com.potopalskyi.movieland.dao;

import com.potopalskyi.movieland.entity.User;
import com.potopalskyi.movieland.entity.UserCredential;

public interface UserDAO {
    User getUserByName(String name);
}
