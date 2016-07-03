package com.potopalskyi.movieland.dao;

import com.potopalskyi.movieland.entity.User;

public interface UserDAO {
    User getUserByName(String name);
}
