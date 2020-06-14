package DAO;

import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    Connection connection;

    public UserDAO(Connection connection) {
        this.connection = connection;
        try {
            dropTable();
            createTable();
        } catch (SQLException exc) {
            System.out.println("База данных недоступна для работы");
        }

    }

    public List<User> getAllUsers() {

       List<User> listUsers = new ArrayList<>();
        try (Statement stmt = connection.createStatement()) {
           ResultSet result = stmt.executeQuery("SELECT * FROM users");

           while(result.next()) {
                listUsers.add(new User(result.getLong(1), result.getString(2),
                        result.getString(3), result.getInt(4)));
           }
        } catch (SQLException exc) {
            exc.printStackTrace();
            return null;
        }
        return listUsers;
    }

    public User getUserById(long id) {
        User user = null;
        try (Statement stmt  = connection.createStatement()) {
            stmt.execute("SELECT * FROM users WHERE id='" + id + "'");
            ResultSet result = stmt.getResultSet();
            result.next();
            user = new User(result.getLong(1), result.getString(2),
                    result.getString(3), result.getInt(4));
        } catch (SQLException exc) {
            exc.printStackTrace();
        }
        return user;
    }

    public boolean addUser(User user) {
        try (Statement stmt = connection.createStatement()) {
            String inser = String.format("INSERT INTO users(name, lastName, age) values('%s', '%s', %d);",
                    user.getName(), user.getLastName(), user.getAge());
            stmt.execute(inser);
        } catch (SQLException exc) {
            exc.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean deleteUser(long id) {
        String update = String.format("DELETE FROM users WHERE id=?;");
        try (PreparedStatement stmt = connection.prepareStatement(update)) {
            stmt.setLong(1, id);
            stmt.execute();
        } catch (SQLException exc) {
              exc.printStackTrace();
              return false;
        }
        return true;
    }

    public boolean updateUser(long id, User user) {
        String update = String.format("UPDATE users SET name=?, lastName=?, age=? WHERE id=?;");
        try (PreparedStatement stmt = connection.prepareStatement(update)) {
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getLastName());
            stmt.setInt(3, user.getAge());
            stmt.setLong(4, id);
            stmt.execute();

        } catch (SQLException exc) {
            exc.printStackTrace();
            return false;
        }
        return true;
    }

    private void dropTable() throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.executeUpdate("DROP TABLE IF EXISTS users");
        stmt.close();
    }

    private void createTable() throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.execute("create table if not exists users (id bigint auto_increment, name varchar(256), lastName varchar(256), age int, primary key (id))");
        stmt.close();
    }
}
