package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;



import java.sql.*;
import java.util.List;
import java.util.ArrayList;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Connection connection = Util.getConnection(); Statement statement = connection.createStatement()) {
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS users" + "(id INT NOT NULL AUTO_INCREMENT, name VARCHAR(45), lastname VARCHAR(45), age INT, PRIMARY KEY (id))");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try (Connection connection = Util.getConnection(); Statement statement = connection.createStatement()) {
            statement.executeUpdate("DROP TABLE IF EXISTS users");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (Connection connection = Util.getConnection(); Statement statement = connection.createStatement()) {
            statement.executeUpdate("INSERT INTO users (name, lastname, age) VALUES ('" + name + "','" + lastName + "','" + age + "')");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id){
        try (Connection connection = Util.getConnection(); Statement statement = connection.createStatement()) {
            statement.executeUpdate("DELETE FROM users WHERE id=" + id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (Connection connection = Util.getConnection(); Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users");

            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                User user = new User(resultSet.getString("name"), resultSet.getString("lastname"), resultSet.getByte("age"));
                user.setId(id);
                users.add(user);
                System.out.println(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } return users;
    }

    public void cleanUsersTable() {
        try (Connection connection = Util.getConnection(); Statement statement = connection.createStatement()) {
            statement.executeUpdate("TRUNCATE TABLE users");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
