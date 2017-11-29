<%@ page import="chai.Services.MemberService" %>
<%@ page import="chai.models.Member" %>
<%@ page import="java.util.List" %>
<%@ page import="chai.Services.ClaimService" %>
<%@ page import="chai.models.Claim" %>
<%@ page import="chai.models.User" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<%
    User loggedInUser = (User) session.getAttribute("loggedInUser");

    if(loggedInUser == null){
        response.sendRedirect("login-page.jsp");
        return;
    }

    if(!loggedInUser.getStatus().equals("ADMIN")){
        response.sendRedirect("dashboard-user-page.jsp");



    MemberService memberService = new MemberService();
    List<Member> members = memberService.getAllMembers();
    pageContext.setAttribute("members", members);

    ClaimService claimService = new ClaimService();
    List<Claim> claims = claimService.getAllClaims();
    pageContext.setAttribute("claims", claims);
    }
%>

<!DOCTYPE html>
<html>
    <head>
        <title>** ADMIN ** DASHBOARD ** ADMIN ** DASHBOARD</title>
        <link type="text/css" rel="stylesheet" href="css/style.css">
    </head>
    <body>
        <div id="wrapper">
            <div id="header">
                <h2>XYZ Driver Association</h2>
                <a href="${pageContext.request.contextPath}/logout">Log out</a>
            </div>
        </div>

            <h3>ADMIN ** DASHBOARD ** ADMIN ** DASHBOARD ** ADMIN ** DASHBOARD</h3>

            <!-- All Users table  -->
            <table>
                <tr></tr>
                <tr>
                    <th><h3>User List</h3></th>
                </tr>
            </table>


                <table>
                    <tr>
                        <th>Name</th>
                        <th>D.O.B</th>
                        <th>Status</th>
                        <th>Balance (�)</th>
                        <th>Action</th>
                    </tr>

                    <c:forEach items="${members}" var="member">
                        <c:if test="${member.status == 'APPLIED'}">
                            <tr>
                                <th>${member.firstName} ${member.lastName}</th>
                                <th>${member.dobString}</th>
                                <th>${member.status}</th>
                                <th>${member.balance}</th>
                                <th>
                                    <form action="${pageContext.request.contextPath}/handle-member" method="POST">
                                        <input type="submit" name="memberAction" value="APPROVE" />
                                        <input type="hidden" name="memberId" value="${member.id}">
                                    </form>
                                </th>
                            </tr>
                        </c:if>
                    </c:forEach>

                    <c:forEach items="${members}" var="member">
                        <c:if test="${member.status == 'APPROVED'}">
                            <tr>
                                <th>${member.firstName} ${member.lastName}</th>
                                <th>${member.dobString}</th>
                                <th>${member.status}</th>
                                <th>${member.balance}</th>
                                <th>
                                    <form action="${pageContext.request.contextPath}/handle-member" method="POST">
                                        <input type="submit" name="memberAction" value="SUSPEND" />
                                        <input type="hidden" name="memberId" value="${member.id}">
                                    </form>
                                </th>
                            </tr>
                        </c:if>
                    </c:forEach>

                    <c:forEach items="${members}" var="member">
                        <c:if test="${member.status == 'SUSPENDED'}">
                            <tr>
                                <th>${member.firstName} ${member.lastName}</th>
                                <th>${member.dobString}</th>
                                <th>${member.status}</th>
                                <th>${member.balance}</th>
                                <th>
                                    <form action="${pageContext.request.contextPath}/handle-member" method="POST">
                                        <input type="submit" name="memberAction" value="UNSUSPEND" />
                                        <input type="hidden" name="memberId" value="${member.id}">
                                    </form>
                                </th>
                            </tr>
                        </c:if>
                    </c:forEach>
                </table>


            <div style="clear: both;"></div> <br>


            <!-- Outstanding balance table -->
                <h3>Outstanding Balance</h3>
                <table>
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Amount (�)</th>
                    </tr>
                    <c:forEach items="${members}" var="member">
                        <c:if test = "${member.balance > 0}">
                            <tr>
                                <th>${member.id}</th>
                                <th>${member.firstName} ${member.lastName}</th>
                                <th>${member.balance}</th>
                            </tr>
                        </c:if>
                    </c:forEach>
                </table>
                
                <div style="clear: both;"></div> <br>

            <!-- Pending claims table  -->
                <h3>Pending Claims</h3>

                        <table>
                            <tr>
                                <th>ID</th>
                                <th>Name</th>
                                <th>Rationale</th>
                                <th>Amount (�)</th>
                                <th></th>
                                <th></th>
                            </tr>

                                <c:forEach items="${claims}" var="claim">
                                    <c:if test = "${claim.status == 'SUBMITTED'}">
                                        <tr >
                                            <th>${claim.id}</th>
                                            <th>${claim.member.firstName} ${claim.member.lastName}</th>
                                            <th>${claim.rationale}</th>
                                            <th>${claim.amount}</th>
                                            <th>
                                                <form action="${pageContext.request.contextPath}/handle-claim" method="POST">
                                                    <input type="submit" name="claimAction" value="ACCEPT" />
                                                    <input type="hidden" name="claimId" value="${claim.id}">
                                                </form>
                                            </th>
                                            <th>
                                                <form action="${pageContext.request.contextPath}/handle-claim" method="POST">
                                                    <input type="submit" name="claimAction" value="REJECT" />
                                                    <input type="hidden" name="claimId" value="${claim.id}">
                                                </form>
                                            </th>
                                        </tr>
                                    </c:if>
                                </c:forEach>

                </table>

        </div>
    </body>
</html>
