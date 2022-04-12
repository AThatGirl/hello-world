package com.example.project.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * JDBCUtils 工具类
 *
 * @author 杰瑞
 * @date 2022/03/27
 */
public class JDBCUtils {
    private static DataSource ds;

    static {

        Properties properties=new Properties();
        try {
            properties.load(JDBCUtils.class.getClassLoader().getResourceAsStream("user.properties"));
            ds= DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

    public static void closeAll(ResultSet resultSet, Statement statement,Connection connection){
        try {
            if (resultSet!=null){
                resultSet.close();
            }
            if (statement!=null){
                statement.close();
            }
            if (statement!=null){
                connection.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static DataSource getDatasource(){
        return ds;
    }
}
