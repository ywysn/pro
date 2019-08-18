package com.cranberry.chatroom.dao;


import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.cranberry.chatroom.utils.CommUtils;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class BaseDao {
    private static DataSource dataSource;
    static {
        Properties properties = CommUtils.
                loadProperties("datasource.properties");
        try {
            dataSource = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            System.err.println("数据源加载失败");
        }
    }

    // 获取数据库连接
    protected Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            System.err.println("获取连接失败");
        }
        return null;
    }

    // 关闭资源Statement ResultSet Connection
    protected void closeResources(Connection connection,
                                  Statement statement) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    protected void closeResources(Connection connection,
                                  Statement statement,
                                  ResultSet resultSet) {
        closeResources(connection,statement);
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
