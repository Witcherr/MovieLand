package com.potopalskyi.movieland.dao.jdbc;

import com.potopalskyi.movieland.dao.UserDAO;
import com.potopalskyi.movieland.dao.jdbc.mapper.UserRowMapper;
import com.potopalskyi.movieland.entity.User;
import com.potopalskyi.movieland.entity.exception.NoDataFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAOImpl implements UserDAO {

    private final Logger logger= LoggerFactory.getLogger(getClass());

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private UserRowMapper userRowMapper;

    @Autowired
    private String getUserByNameSQL;

    @Override
    public User getUserByName(String name) {
        logger.info("Start query for getting user with name = {}", name);
        try {
            return jdbcTemplate.queryForObject(getUserByNameSQL, new Object[]{name}, userRowMapper);
        }catch (EmptyResultDataAccessException e){
            logger.warn("There is no user with name = {} in database", name);
            throw new NoDataFoundException("There is no user with name =" + name, e);
        }
    }
}
