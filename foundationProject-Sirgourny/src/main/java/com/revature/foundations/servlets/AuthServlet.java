package com.revature.foundations.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServlet;

/**
 * The Auth Servlet class is meant to be an accessible authentication endpoint for our server.
 * Extends the HttpServlet
 */
public class AuthServlet extends HttpServlet {

    // Instantiating the required global objects
    private final ObjectMapper objectMapper;
    private final AuthService authService;

    // This constructor is needed when creating a new servlet object
    public AuthServlet(AuthService authService, ObjectMapper objectMapper) {
        // object mapper assignment
        this.objectMapper = objectMapper;
        // auth service assignment
        this.authService = authService;
    }
}
