package chai.controllers;

import chai.Services.AuthenticationService;
import chai.models.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class AdminAuthenticationController extends HttpServlet {

    /**
     * Acquire userId and password from POST parameter
     * and call authentication.service.logIn()
     * Redirect user to home page if success
     * Error message if failed
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String userId   = request.getParameter("userId");
        String password = request.getParameter("password");

        AuthenticationService authenticationService = new AuthenticationService();
        User admin = authenticationService.adminLogin(userId, password);

        // Redirect to error log in page if user not found
        if(admin == null){
            response.sendRedirect(request.getContextPath() + "/admin-login-page-error.jsp");
            return;
        }


        // Redirect to dashboard page after logged in.
        // Attach User object in session, so its accisable in JSP
        HttpSession session = request.getSession();
        session.setAttribute("loggedInUser", admin);

        request.getRequestDispatcher("dashboard-admin-page.jsp").forward(request, response);

    }
}
