package chai.controllers;

import chai.Services.ClaimService;
import chai.Services.MemberService;
import chai.models.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class HandleMemberController extends HttpServlet{

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String memberAction   = request.getParameter("memberAction");
        String memberId       = request.getParameter("memberId");

        memberAction = memberAction.equals("SUSPEND")  ? "SUSPENDED" : "APPROVED";

        System.out.println("========Member Action=============" + memberAction);
        System.out.println("========Member id=============" + memberId);

        HttpSession session = request.getSession();
        User admin = (User) session.getAttribute("loggedInUser");

        if(!admin.getStatus().equals("ADMIN")){
            System.out.println("not admin");
            request.getRequestDispatcher("dashboard-admin-page.jsp").forward(request, response);
            return;
        }

        MemberService memberService = new MemberService();
        memberService.updateMemberStatus(memberAction, memberId);


        request.getRequestDispatcher("dashboard-admin-page.jsp").forward(request, response);

    }
}
