package com.mjh.dao.impl;

import com.mjh.utils.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author mjh
 * @date 2021-10-02 14:26
 */
public abstract class BaseDao {
    private QueryRunner queryRunner = new QueryRunner();

    public int update(String sql, Object... args) {
        Connection con = JdbcUtils.getConnection();
        try {
            return queryRunner.update(con, sql, args);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }


    public <T> T queryForOne(Class<T> type, String sql, Object... args) {
        Connection con = JdbcUtils.getConnection();
        try {
            return queryRunner.query(con, sql, new BeanHandler<T>(type), args);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public <T> List<T> queryForList(Class<T> type, String sql, Object... args) {
        Connection con = JdbcUtils.getConnection();
        try {
            return queryRunner.query(con, sql, new BeanListHandler<T>(type), args);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public Object queryForSingleValue(String sql,Object...args){
        Connection con=JdbcUtils.getConnection();
        try {
            return queryRunner.query(con,sql,new ScalarHandler<>(),args);
        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
