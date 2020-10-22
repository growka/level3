package server.service;

import server.inter.DBService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBServiceImpl implements DBService {

    public static Connection getInstance() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            //return DriverManager.getConnection("jdbc:mysql://localhost:3306/user_db", "root", "123456");
            return DriverManager.getConnection("jdbc:mysql://localhost/user_db?useUnicode=true&serverTimezone=UTC&useSSL=false", "root", "123456");
        } catch (Exception e) {
            e.printStackTrace();

        } return null;
    }
    @Override
    public ArrayList<UserEntity> findAll() {

        ArrayList<UserEntity> users = new ArrayList<UserEntity>();
        Connection connection = null;

        try {
            connection = getInstance();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users");

            while (resultSet.next()) {
                UserEntity user = new UserEntity(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("password")
                );
                users.add(user);
            }

        } catch (SQLException e) {
            throw new RuntimeException("SWW during DB-query");
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
        return users;
    }

    @Override
    public UserEntity findUser(String login) {

        Connection connection = null;
        UserEntity user = new UserEntity();

        try {
            connection = getInstance();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users WHERE name = " + login);
            resultSet.next();

            user.setId(resultSet.getInt("id"));
            user.setName(resultSet.getString("name"));
            user.setPassword(resultSet.getString("password"));

        } catch (SQLException e) {
            //throw new RuntimeException("SWW during DB-query");
            e.printStackTrace();
            return null;
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
        return user;
    }

    @Override
    public boolean add(UserEntity user) {
        Connection connection = null;
        try {
            connection = getInstance();
            PreparedStatement statement = connection.prepareStatement("INSERT INTO users (id, name, password) VALUES (?,?,?)");
            statement.setInt(1, user.getId());
            statement.setString(2, user.getName());
            statement.setString(3, user.getPassword());
            return statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException("SWW during DB-query");
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
    }

    @Override
    public boolean updateUserName(UserEntity user) {
        return false;
    }
}
