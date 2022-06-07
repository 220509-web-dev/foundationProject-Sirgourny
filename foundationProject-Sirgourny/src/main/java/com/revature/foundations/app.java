package com.revature.foundations;

import com.revature.foundations.daos.UserDAO;
import com.revature.foundations.daos.UserDaoPostgres;
import com.revature.foundations.models.User;

import java.sql.SQLException;

public class app {
    public static void main(String[] args) throws SQLException {

        // test if getUserById works
        UserDAO userDAO = new UserDaoPostgres();
        User user = new User();
        user = userDAO.getUserById(1);
        System.out.println(user.toString());


/*
        // test if createUser works
        UserDAO userDAO = new UserDaoPostgres();
        User user = new User();
        user.setUser_id(0);
        user.setFirstname("Sirgourny");
        user.setLastname("Compton");
        user.setEmail("Scomp@revature.net");
        user.setUsername("Scomp123");
        user.setPassword("pass123");
        System.out.println("User before:");

        System.out.println(user.toString());
        user = userDAO.createUser(user);

        System.out.println("User after:");
        System.out.println(user.toString());
*/

    }
}
