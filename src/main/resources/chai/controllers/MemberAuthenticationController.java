package chai.controllers;


import chai.Services.AuthenticationService;
import chai.dao.UserDAO;
import chai.models.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

// Maps to /authenticate
public class MemberAuthenticationController extends HttpServlet{

    /**
     * Acquire userId and password from POST parameter
     * and call authentication.service.logIn()
     * Redirect user to home page if success
     * Error message if failed
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("application/json");
        String userId   = request.getParameter("userId");
        String password = request.getParameter("password");

        PrintWriter writer = response.getWriter();
        AuthenticationService authenticationService = new AuthenticationService();
        User user = authenticationService.userLogIn(userId, password);

        // Redirect to error log in page if user not found
        if(user == null){
            response.sendRedirect(request.getContextPath() + "/login-page-error.jsp");
           return;
        }


        // Redirect to dashboard page after logged in.
        // Attach User object in variable session for 20 minutes
        HttpSession session = request.getSession();
        session.setAttribute("loggedInUser", user);

        request.getRequestDispatcher("dashboard-user-page.jsp").forward(request, response);

    }

}
