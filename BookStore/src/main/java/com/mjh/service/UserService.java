package com.mjh.service;

import com.mjh.pojo.entity.domain.bean.User;

/**
 * @author mjh
 * @date 2021-10-02 15:12
 */
public interface UserService {
    public void register(User user);
    public User login(User user);
    public boolean exitUsername(String username);
}
