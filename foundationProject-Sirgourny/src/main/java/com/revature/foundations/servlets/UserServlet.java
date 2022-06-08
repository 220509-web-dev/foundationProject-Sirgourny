package com.revature.foundations.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.foundations.dto.ResourceCreationResponse;
import com.revature.foundations.models.User;
import com.revature.foundations.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserServlet extends HttpServlet {

    private final ObjectMapper mapper;

    private final UserService userService;

    public UserServlet(ObjectMapper mapper, UserService userService) {
        this.mapper = mapper;
        this.userService = userService;
    }

    @Override
    public void init() throws ServletException {
        System.out.println("[LOG] - UserServlet instantiated!");
        System.out.println("[LOG] - Init param, test-init-key: " + this.getServletConfig().getInitParameter("test-init-key"));
        System.out.println("[LOG] - Init param, user-servlet-key: " + this.getServletConfig().getInitParameter("user-servlet-key"));
        System.out.println("[LOG] - Init param, another-param: " + this.getServletConfig().getInitParameter("another-param"));
        System.out.println("[LOG] - Context param, test-context-key: " + this.getServletContext().getInitParameter("test-context-key"));
    }



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //get User - it is newUser - use the mapper to read the value from the input stream of the request - tell jackson to turn it into a user
        User newUser = mapper.readValue(req.getInputStream(), User.class);
        // if the user is valid, we use the userService to pass it through and validate it
        ResourceCreationResponse payload = userService.createNewUser(newUser);
        resp.setStatus(201); // This basically says that something has been CREATED
        resp.setContentType("application/json");
        resp.getWriter().write(mapper.writeValueAsString(payload));

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//        System.out.println("[LOG] - UserServlet received a POST request at " + LocalDateTime.now());
//
//        try {
//            AppUser newUser = mapper.readValue(req.getInputStream(), AppUser.class);
//            System.out.println(newUser);
//        } catch (Exception e) {
//            logError(e);
//            e.printStackTrace();
//        }
//        resp.setStatus(204);

    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPut(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }
}