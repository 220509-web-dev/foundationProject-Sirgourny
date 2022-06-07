package com.revature.foundations.apps;

import com.revature.foundations.daos.UserDAO;
import com.revature.foundations.daos.UserDaoPostgres;
import com.revature.foundations.models.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.revature.foundations.logger.CustomLogger.logError;

public class TestApp {
    static int test_id;
    static String username = "TestU";
    public static void main(String[] args) {
        test_createUser();
        test_getUserById();
        test_getUserByUsername();
        test_getAllUsers();
    }

    private static void test_createUser() {
        try {
            UserDAO userDAO = new UserDaoPostgres();
            User user = new User(1, "testf", "testl", "testemai", username, "TestP");
            user = userDAO.createUser(user);
            System.out.println("test_createUser: "+user);
            test_id = user.getUser_id();
        } catch (SQLException e) {
            logError(e);
            throw new RuntimeException(e);
        }

    }
    private static void test_getUserById() {
        UserDAO userDAO = new UserDaoPostgres();
        User user = userDAO.getUserById(test_id);
        System.out.println("test_getUserById: "+user);

    }
    private static void test_getUserByUsername() {
        UserDAO userDAO = new UserDaoPostgres();
        User user = userDAO.getUserByUsername(username);
        System.out.println("test_getUserByUsername: "+user);

    }
    private static void test_getAllUsers() {
        UserDAO userDAO = new UserDaoPostgres();
        List<User> users = new ArrayList<>();
        users = userDAO.getAllUsers();
        users.forEach(System.out::println);
    }
    private static void test_updateUser() {

    }
    private static void test_deleteUserById() {

    }

}

/*
   User createUser(User user) throws SQLException;
    User getUserById(int id);
    User getUserByUsername(String username);
    List<User> getAllUsers();
    User updateUser(User user);
    void deleteUserById(int id);
 */
