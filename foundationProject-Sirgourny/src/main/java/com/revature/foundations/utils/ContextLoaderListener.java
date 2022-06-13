package com.revature.foundations.utils;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.foundations.daos.UserDaoPostgres;
import com.revature.foundations.services.UserService;
import com.revature.foundations.servlets.AuthServlet;

import com.revature.foundations.servlets.SanityServlet;
import com.revature.foundations.servlets.UserServlet;

import javax.servlet.*;
import java.time.LocalDateTime;

public class ContextLoaderListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        System.out.println("[LOG] - The servlet context was initialized at " + LocalDateTime.now());

        ObjectMapper mapper = new ObjectMapper();
        UserDaoPostgres userDAOPostgres = new UserDaoPostgres();
        UserService userService = new UserService(userDAOPostgres);

        ServletContext context = sce.getServletContext();

        // Sanity Servlet
        SanityServlet sanityServlet = new SanityServlet();
        context.addServlet("SanityServlet", sanityServlet).addMapping("/sanity");

        // Auth Servlet
        AuthServlet authServlet = new AuthServlet(mapper, userDAOPostgres);
        context.addServlet("AuthServlet", authServlet).addMapping("/auth");

        // User Servlet
        UserServlet userServlet = new UserServlet(mapper, userService);
        context.addServlet("UserServlet", userServlet).addMapping("/users");


    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("[LOG] - The servlet context was destroyed at " + LocalDateTime.now());
    }

}