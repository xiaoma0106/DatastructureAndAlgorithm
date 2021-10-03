package com.mjh.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;

/**
 * @author mjh
 * @date 2021-10-02 13:29
 */
public class JdbcUtils {
    private static DataSource dataSource;

    static {
        try {
            Properties properties = new Properties();
            InputStream is = JdbcUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
            properties.load(is);
            dataSource= DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection(){
        Connection con=null;

        try {
            con=dataSource.getConnection();
        }catch (Exception e){
            e.printStackTrace();
        }
        return con;
    }

    public static void close(Connection con){
        if (con != null){
            try {
                con.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

}
