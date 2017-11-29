<%@ page import="chai.models.User" %>
<%
    User loggedinUser = (User) session.getAttribute("loggedInUser");

    if(loggedinUser == null){
        response.sendRedirect("login-page.jsp");
        return;
    }

    if(loggedinUser.getStatus().equals("ADMIN")){
        response.sendRedirect("dashboard-admin-page.jsp");
        return;
    }

    response.sendRedirect("dashboard-user-page.jsp");
%>