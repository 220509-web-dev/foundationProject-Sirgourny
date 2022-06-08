package com.revature.foundations.utils;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.foundations.daos.UserDaoPostgres;
import com.revature.foundations.services.UserService;
import com.revature.foundations.servlets.AuthServlet;

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
        UserServlet userServlet = new UserServlet(mapper, userService);

        AuthServlet authServlet = new AuthServlet(mapper);

        ServletContext context = sce.getServletContext();

//        CustomFilter customFilter = new CustomFilter();
//        context.addFilter("CustomFilter", customFilter)
//               .addMappingForUrlPatterns(EnumSet.of(DispatcherType.), true, "/*");

        context.addServlet("NewServlet", newServlet).setLoadOnStartup(1);

        context.addServlet("AuthServlet", authServlet).addMapping("/auth");

        ServletRegistration.Dynamic registeredServlet = context.addServlet("UserServlet", userServlet);
        registeredServlet.setLoadOnStartup(3);
        registeredServlet.setInitParameter("user-servlet-key", "user-servlet-value");
        registeredServlet.setInitParameter("another-param", "another-value");
        registeredServlet.addMapping("/users/*");

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("[LOG] - The servlet context was destroyed at " + LocalDateTime.now());
    }

}