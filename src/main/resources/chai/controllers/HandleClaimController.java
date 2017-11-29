package chai.controllers;

import chai.Services.AuthenticationService;
import chai.Services.ClaimService;
import chai.models.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class HandleClaimController extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String claimAction   = request.getParameter("claimAction");
        String claimId   = request.getParameter("claimId");

        claimAction = claimAction.equals("ACCEPT")  ? "ACCEPTED" : "REJECTED";

        HttpSession session = request.getSession();
        User admin = (User) session.getAttribute("loggedInUser");

        if(!admin.getStatus().equals("ADMIN")){
            request.getRequestDispatcher("dashboard-admin-page.jsp").forward(request, response);
            return;
        }

        ClaimService claimService = new ClaimService();
        claimService.updateClaimStatus(claimAction, Integer.parseInt(claimId));


        request.getRequestDispatcher("dashboard-admin-page.jsp").forward(request, response);

    }
}
