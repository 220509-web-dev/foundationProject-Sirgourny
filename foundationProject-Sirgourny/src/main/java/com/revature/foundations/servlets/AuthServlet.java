package com.revature.foundations.servlets;

/**
 * The Auth Servlet class is meant to be an accessible authentication endpoint for our server.
 * Extends the HttpServlet
 */


import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.foundations.daos.UserDaoPostgres;
import com.revature.foundations.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


public class AuthServlet extends HttpServlet {

    private final ObjectMapper mapper;
    private UserDaoPostgres userDao;

    public AuthServlet(ObjectMapper mapper, UserDaoPostgres userDao) {
        this.mapper = mapper;
        this.userDao = userDao;
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);

        if (session != null) {
            session.invalidate();
        }

        resp.setStatus(204);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<User> users = new ArrayList<>();
        users.addAll(Arrays.asList(
                new User(1, "Briana", "Manina", "BrianaManin@fofo.com", "BrianaMa", "Briana00"),
                new User(2, "Contrea", "Crosby", "ContreaC@boo.com", "ContreaC", "Contrea1")
        ));
        


        HashMap<String, Object> credentials = mapper.readValue(req.getInputStream(), HashMap.class);
        String providedUsername = (String) credentials.get("username");
        String providedPassword = (String) credentials.get("password");

        User authUser = userDao.getUserByUsernameAndPassword(providedUsername, providedPassword);
        System.out.println("user from db - " + authUser);
        if (authUser != null) {
            // Because HTTP is a stateless protocol, we need a session to persist data across multiple requests
            HttpSession session = req.getSession(); // use req.getSession(false) to prevent a session from being made
            session.setAttribute("auth-user", authUser); // this attribute is visible on any requests with this session attached

            resp.setStatus(204); // NO CONTENT (success but nothing to return)
            return;
        }

        resp.setStatus(400);
        resp.setContentType("application/json");

        HashMap<String, Object> errorMessage = new HashMap<>();
        errorMessage.put("code", 400);
        errorMessage.put("message", "No user found with provided credentials");
        errorMessage.put("timestamp", LocalDateTime.now().toString());

        resp.getWriter().write(mapper.writeValueAsString(errorMessage));




    }


}