package com.mjh.dao;

import com.mjh.pojo.entity.domain.bean.User;

/**
 * @author mjh
 * @date 2021-10-02 14:42
 */
public interface UserDao {

    public User queryUserByUsername(String username);
    public User queryUserByUsernameAndPassword(String username,String password);
    public int saveUser(User user);
}
