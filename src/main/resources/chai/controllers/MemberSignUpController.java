package chai.controllers;


import chai.Services.CredentialsGeneratorService;
import chai.Services.DateService;
import chai.Services.MemberService;
import chai.Services.RegistrationService;

import chai.models.Member;
import chai.models.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MemberSignUpController extends HttpServlet {

    // Register new member, generate their credentials and redirect them to signup-page-success page
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String firstName     = request.getParameter("firstName");
        String lastName      = request.getParameter("lastName");
        String dobString     = request.getParameter("dob");
        String address       = request.getParameter("address");

        String userId        = firstName.substring(0,2).toLowerCase() + "-" + lastName.toLowerCase();
        DateService dateService = new DateService("yyyy-MM-dd");
        Member newMember     = null;

        try {
            newMember     = new Member(userId,
                    firstName,
                    lastName,
                    address,
                    dateService.stringToDate(dobString),
                    new Date(),
                    "APPLIED",
                    10);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        RegistrationService registrationService = new RegistrationService();
        User newUser = registrationService.registerMember(newMember);

        // Redirect to dashboard page after logged in.
        // Attach User object in variable loggedInUser, so its accisable in JSP
        request.setAttribute("newUser", newUser);
        request.getRequestDispatcher("signup-page-success.jsp").forward(request, response);


    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        MemberService memberService = new MemberService();
        PrintWriter writer = response.getWriter();

        writer.println(memberService.getAllMembers());
    }

}
