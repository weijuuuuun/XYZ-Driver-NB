<%@ page import="chai.Services.PaymentService" %>
<%@ page import="chai.models.Payment" %>
<%@ page import="java.util.List" %>
<%@ page import="chai.models.User" %>
<%@ page import="chai.Services.ClaimService" %>
<%@ page import="chai.models.Claim" %>
<%@ page import="chai.models.Member" %>
<%@ page import="chai.Services.MemberService" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%

    User loggedInUser = (User) session.getAttribute("loggedInUser");

    if(loggedInUser == null){
        response.sendRedirect("login-page.jsp");
        return;
    }

    if(loggedInUser.getStatus().equals("ADMIN")){
        response.sendRedirect("dashboard-admin-page.jsp");
    }

    PaymentService paymentService = new PaymentService();
    List<Payment> payments = paymentService.getPaymentOfMember(loggedInUser.getId());

    ClaimService claimService = new ClaimService();
    List<Claim> claims = claimService.getAllClaims();

    MemberService memberService = new MemberService();
    Member member = memberService.get(loggedInUser.getId());

    pageContext.setAttribute("payments", payments);
    pageContext.setAttribute("claims", claims);
    pageContext.setAttribute("loggedInUser", loggedInUser);
    pageContext.setAttribute("member", member);
    pageContext.setAttribute("balance", member.getBalance());

%>

<!DOCTYPE html>
<html>
    <head>
        <title>Dashboard - ${member.firstName} ${member.lastName} </title>
        <link type="text/css" rel="stylesheet" href="css/style.css">
    </head>
    <body>
        <div id="wrapper">
            <div id="header">
                <h2>XYZ Driver Association</h2>
                <a href="${pageContext.request.contextPath}/logout">Log out</a>
            </div>
        </div>
        <div id="container">
            <h3>DashBoard - <span id="userId">${member.id}</span> (${member.status}) </h3>
                <table>
                    <tbody>
                        <tr></tr>
                        <tr>
                            <td><label>Outstanding:</label></td>
                            <td><label>${member.balance}</label></td>
                            <td>

                                <form action="${pageContext.request.contextPath}/member-pay" method="POST">
                                    <input type="hidden" name="memberId" value="${member.id}">
                                    <input type="hidden" name="amount" value="${member.balance}">
                                    <c:if test="${balance gt 0}">
                                        <input type="submit" value="PAY" />
                                    </c:if>
                                </form>
                            </td>
                        </tr>
                    </tbody>
                </table>
                
                <div style="clear: both;"></div>
                <div style="clear: both;"></div>
                
                <h3>All Payments</h3>
                    <table>

                            <tr>
                                <th>ID</th>
                                <th>Payment Type</th>
                                <th>Amount (�)</th>
                                <th>Date</th>
                                <th>Time</th>
                            </tr>
                            <c:forEach items="${payments}" var="payment">
                                <tr>
                                    <th>${payment.id}</th>
                                    <th>${payment.typeOfPayment}</th>
                                    <th>${payment.amount}</th>
                                    <th>${payment.dateString}</th>
                                    <th>${payment.timeString}</th>
                                </tr>
                            </c:forEach>
                    </table>
                
                <div style="clear: both;"></div>


                <h3>All Claims</h3>
                <table>
                    <tr>
                        <th>ID</th>
                        <th>Rationale</th>
                        <th>Amount (�)</th>
                        <th>Status</th>
                        <th>Date</th>
                    </tr>


                    <c:forEach items="${claims}" var="claim">
                        <c:if test="${claim.member.id eq loggedInUser.id}">
                            <tr>
                                <th>${claim.id}</th>
                                <th>${claim.rationale}</th>
                                <th>${claim.amount}</th>
                                <th>${claim.status}</th>
                                <th>${claim.dateString}</th>
                            </tr>
                        </c:if>
                    </c:forEach>
                </table>


            <h3>Submit Claim</h3>
            <p style="color: red; display: none;" id="uneligible">Uneligible for claim.</p>
            <form action="${pageContext.request.contextPath}/submit-claim" method="POST" id="submitClaim">
                <table>
                    <tbody>
                    <tr>
                        <td><label>Rationale </label></td>
                        <td><input type="text" name="rationale"/></td>
                    </tr>
                    <tr>
                        <td><label>Amount </label></td>
                        <td><input type="text" name="amount"/></td>
                    </tr>
                    <tr>
                        <td><label></label></td>
                        <td><input type="submit" value="Submit" class="btn"/></td>
                    </tr>
                    </tbody>
                </table>
            </form>

        </div>
    </body>
</html>

<script src="https://code.jquery.com/jquery-3.2.1.min.js"> </script>

<script>

    $(document).ready(function () {

        var userId = $("#userId").text();
        console.log("http://localhost:8080/xyz-driver/claim/eligibility?member-id="+userId);

        $.get( "http://localhost:8080/xyz-driver/claim/eligibility?member-id="+userId, function( data ) {

            if(data === false){
                $("#submitClaim").hide();
                $("#uneligible").show();
            }

        });

    });

</script>