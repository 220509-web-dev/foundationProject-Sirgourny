package com.revature.foundations.servlets;

/**
 * The Auth Servlet class is meant to be an accessible authentication endpoint for our server.
 * Extends the HttpServlet
 */


import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.foundations.models.AppUser;

import javax.servlet.ServletException;
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

    public AuthServlet(ObjectMapper mapper) {
        this.mapper = mapper;
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

        // Hard-coded example users (that totally come from the database)
        List<AppUser> users = new ArrayList<>();
        users.addAll(Arrays.asList(
                new AppUser(1, "Briana", "Mania", "BrianaManin@fofo.com", "BrianaMa", "Briana00"),
                new AppUser(2, "Contrea", "Crosby", "ContreaC@boo.com", "ContreaC", "Contrea1"),
                new AppUser(3, "Shawnda", "Wilson", "ShawndaW@gogo.com", "ShawndaW", "Shawnda5"),
                new AppUser(4, "Kentrell", "Bennette", "Kentrell@tootoo.com", "KenBen", "KenB9012"),
                new AppUser(5, "Saraphina", "Jones", "SaraphinaL@roo.com", "SaraLan", "SaraL345")
        ));

        // Totally valid to create a custom DTO to represent a Credentials request body
        // but HashMaps work too.
        HashMap<String, Object> credentials = mapper.readValue(req.getInputStream(), HashMap.class);
        String providedUsername = (String) credentials.get("username");
        String providedPassword = (String) credentials.get("password");

        for (AppUser user : users) {
            if (providedUsername.equals(user.getUsername()) && providedPassword.equals(user.getPassword())) {
                System.out.println("[LOG] - found user!");

                // Because HTTP is a stateless protocol, we need a session to persist data across multiple requests
                HttpSession session = req.getSession(); // use req.getSession(false) to prevent a session from being made
                session.setAttribute("auth-user", user); // this attribute is visible on any requests with this session attached

                resp.setStatus(204); // NO CONTENT (success but nothing to return)
                return; // return here otherwise we continue and bad things might happen
            }
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