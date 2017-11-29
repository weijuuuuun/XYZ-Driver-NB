package chai.controllers;

import chai.Services.ClaimService;
import chai.Services.PaymentService;
import chai.models.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class MemberPaymentController extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String memberId   = request.getParameter("memberId");
        String amount     = request.getParameter("amount");

        PaymentService paymentService = new PaymentService();
        paymentService.pay(memberId, Float.parseFloat(amount));


        request.getRequestDispatcher("dashboard-user-page.jsp").forward(request, response);

    }
}
