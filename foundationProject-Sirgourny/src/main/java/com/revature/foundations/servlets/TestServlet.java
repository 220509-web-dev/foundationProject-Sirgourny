package com.revature.foundations.servlets;

import com.revature.foundations.daos.UserDAO;
import com.revature.foundations.daos.UserDaoPostgres;
import com.revature.foundations.models.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TestServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        System.out.println("[LOG] - TestServlet init method ran");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("[LOG] - TestServlet doGet method ran");

        // see what kind of request we got
        // based on that, call service layer for one of :
        // getUserById, getUserByUsername, or getAllUsers
        // if request is for ID, do //serviceGetUserByID(input_from_request);
        // etc.
        // TODO : move to service layer
        // because servlet just needs to handle
        UserDAO userDAO = new UserDaoPostgres(); //DAO should be handled by service layer
        User user = userDAO.getUserById(1);
        String wrteethis = user.toString();
        resp.setContentType("text/json");
        resp.getWriter().write(wrteethis);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("[LOG] - TestServlet doPost method ran");

        // for example, create user
        String writethis = "Here is the response from service layer";
        resp.setContentType("text/json");
        resp.getWriter().write(writethis);

    }
}
