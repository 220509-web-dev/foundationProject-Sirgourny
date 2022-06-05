package com.revature.foundations.daos;


import com.revature.foundations.models.User;

import java.util.List;

public interface UserDAO {
    User createUser(User user);
    User getUserById(int id);
    User getUserByUsername(String username);
    List<User> getAllUsers();
    User updateUser(User user);
    void deleteUserById(int id);
}