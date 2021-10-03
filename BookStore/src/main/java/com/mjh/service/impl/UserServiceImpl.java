package com.mjh.service.impl;

import com.mjh.dao.impl.UserDaoImpl;
import com.mjh.pojo.entity.domain.bean.User;
import com.mjh.service.UserService;

/**
 * @author mjh
 * @date 2021-10-02 15:11
 */
public class UserServiceImpl implements UserService {
    private UserDaoImpl userDao = new UserDaoImpl();

    @Override
    public void register(User user) {
        userDao.saveUser(user);
    }

    @Override
    public User login(User user) {
        return userDao.queryUserByUsernameAndPassword(user.getUsername(),user.getPsd());
    }

    @Override
    public boolean exitUsername(String username) {
        if (null != userDao.queryUserByUsername(username)) {
            return true;
        } else {
            return false;
        }
    }
}
