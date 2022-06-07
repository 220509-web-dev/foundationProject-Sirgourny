package com.revature.foundations.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import static com.revature.foundations.logger.CustomLogger.logError;

public class NewServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
        System.out.println("new Servlet init");
        logError("init done");
    }
}
