package com.potopalskyi.movieland.dao;

import com.potopalskyi.movieland.entity.business.User;

public interface UserDAO {
    User getUserByName(String name);
}
