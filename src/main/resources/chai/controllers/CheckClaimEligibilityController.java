package chai.controllers;

import chai.Services.ClaimService;
import chai.Services.DateService;
import chai.Services.MemberService;
import chai.models.Claim;
import chai.models.Member;
import chai.models.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

public class CheckClaimEligibilityController extends HttpServlet {
    

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        MemberService memberService = new MemberService();

        String memberId = request.getParameter("member-id");

        response.setContentType("application/json");
        PrintWriter writer = response.getWriter();

        writer.println(memberService.getClaimEligibility(memberId));
    }
}
