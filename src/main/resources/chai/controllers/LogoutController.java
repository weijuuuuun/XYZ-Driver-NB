package chai.controllers;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LogoutController extends HttpServlet{

    // Invalidate session and redirect to member login
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        request.getSession().invalidate();
        request.getRequestDispatcher("login-page.jsp").forward(request, response);
    }

}
