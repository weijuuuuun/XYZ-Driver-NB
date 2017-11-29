package chai.controllers;

import chai.Services.ClaimService;
import chai.Services.DateService;
import chai.Services.MemberService;
import chai.Services.RegistrationService;
import chai.models.Claim;
import chai.models.Member;
import chai.models.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.Date;

public class SubmitClaimController extends HttpServlet {

    // Register new member, generate their credentials and redirect them to signup-page-success page
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {




        String rationale     = request.getParameter("rationale");
        String amount        = request.getParameter("amount");
        String status        = "SUBMITTED";

        User loggedInUser = (User) request.getSession().getAttribute("loggedInUser");
        String memberId = loggedInUser.getId();

        Member member = new Member();
        member.setId(memberId);

        Claim claim = new Claim();
        claim.setRationale(rationale);
        claim.setAmount(Float.parseFloat(amount));
        claim.setStatus(status);
        claim.setMember(member);
        claim.setDate(new Date());

        ClaimService claimService = new ClaimService();
        claimService.addClaim(claim);

        request.getRequestDispatcher("dashboard-user-page.jsp").forward(request, response);



    }


}
