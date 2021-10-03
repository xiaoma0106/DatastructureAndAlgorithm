package com.mjh.dao.impl;

import com.mjh.dao.UserDao;
import com.mjh.pojo.entity.domain.bean.User;

/**
 * @author mjh
 * @date 2021-10-02 14:45
 */
public class UserDaoImpl extends BaseDao implements UserDao {
    @Override
    public User queryUserByUsername(String username) {
       String sql="select * from t_user where username=?";
       return queryForOne(User.class,sql,username);
    }

    @Override
    public User queryUserByUsernameAndPassword(String username, String password) {
        String sql="select * from t_user where username=? and psd=?";
        return queryForOne(User.class,sql,username,password);
    }

    @Override
    public int saveUser(User user) {
        String sql="insert into t_user(username,psd,email) values(?,?,?)";
        return update(sql,user.getUsername(),user.getPsd(),user.getEmail());
    }
}
