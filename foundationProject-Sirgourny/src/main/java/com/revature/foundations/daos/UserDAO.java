package com.revature.foundations.daos;


import com.revature.foundations.models.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDAO {
    User createUser(User user) throws SQLException;
    User getUserById(int id);
    User getUserByUsername(String username);
    List<User> getAllUsers();
    User updateUser(User user);
    void deleteUserById(int id);
}