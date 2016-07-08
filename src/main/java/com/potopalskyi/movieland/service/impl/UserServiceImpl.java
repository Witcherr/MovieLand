package com.potopalskyi.movieland.service.impl;

import com.potopalskyi.movieland.dao.UserDAO;
import com.potopalskyi.movieland.entity.business.User;
import com.potopalskyi.movieland.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Override
    public User getUserByName(String name) {
        return userDAO.getUserByName(name);
    }
}
