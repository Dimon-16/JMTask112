package service;

import DAO.UserDAO;
import model.User;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class UserService {

    private UserDAO dao;
    public static UserService userService;

    private UserService() {
        dao = getUserDAO(getConnection());
    }

    public static UserService getInstance() {
        if (userService == null) {
            userService = new UserService();
        }
        return userService;
    }



    public boolean updateTable(long id, User user) {
        User oldUser = dao.getUserById(id);
        if (oldUser != null) {
            dao.updateUser(id, user);
        } else {
            return false;
        }
        return true;
    }

    public boolean deleteUser(long id) {
        User oldUser = dao.getUserById(id);
        if (oldUser != null) {
            dao.deleteUser(id);
        } else {
            return false;
        }
        return true;
    }

    public List<User> getAllUsers(){
        return dao.getAllUsers();
    }

    public boolean addUser(User user) {
        return dao.addUser(user);
    }

    private static Connection getConnection() {
        try {
            DriverManager.registerDriver((Driver) Class.forName("com.mysql.jdbc.Driver").newInstance());

            StringBuilder url = new StringBuilder();
            url.append("jdbc:mysql://").
                append("localhost:").
                append("3306/").
                append("bankclient?").
                append("user=root&").
                append("password=DvLottery2021");

            Connection connection = DriverManager.getConnection(url.toString());
            return connection;
        } catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException exc) {
            exc.printStackTrace();
            throw new IllegalStateException();
        }
    }

    private static UserDAO getUserDAO(Connection connection) {
        return new UserDAO(connection);
    }
}
