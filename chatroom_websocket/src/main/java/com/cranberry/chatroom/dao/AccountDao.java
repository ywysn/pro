package com.cranberry.chatroom.dao;
import com.cranberry.chatroom.entity.User;
import org.apache.commons.codec.digest.DigestUtils;

import java.sql.*;

public class AccountDao extends BaseDao {
    public User userLogin(String username,String password){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        User user = null;

        try {
            connection = getConnection();
            String sql = "SELECT * FROM user WHERE username = ? AND password = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1,username);
            statement.setString(2,DigestUtils.md5Hex(password));
            resultSet = statement.executeQuery();
            if(resultSet.next())
                    user = getUserInfo(resultSet);
        }catch (SQLException e){
                    e.printStackTrace();
                    System.out.println("查询用户信息出错");
        }finally {
            closeResources(connection,statement,resultSet);
        }
        return user;
    }

    public boolean userRegister(User user){
        String userName = user.getUsername();
        String password = user.getPassword();
        Connection connection = null;
        PreparedStatement statement = null;
        boolean isSuccess = false;
        try{
            connection = getConnection();
            String sql = "INSERT INTO user(username, password)" + " VALUES (?,?)";
            statement = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            statement.setString(1,userName);
            statement.setString(2,DigestUtils.md5Hex(password));
            isSuccess = (statement.executeUpdate() == 1);
        } catch (SQLException e) {
            System.err.println("用户注册失败");
        }finally {
            closeResources(connection,statement);
        }
        return isSuccess;
    }

    public User getUserInfo(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getInt("id"));
        user.setUsername(resultSet.getString("username"));
        user.setPassword(resultSet.getString("password"));
        return user;
    }
}
