<!DOCTYPE html>
<html>
    <head>
        <title>Signup</title>
        <link type="text/css" rel="stylesheet" href="css/style.css">
        <link type="text/css" rel="stylesheet" href="css/login-style.css">
        
    </head>
    <body>
        <div id="wrapper">
            <div id="header">
                <h2>XYZ Driver Association</h2>
            </div>
        </div>
        <div id="container">
            <h3>Sign Up</h3>
            <form action="${pageContext.request.contextPath}/sign-up-member" method="POST">
                <table>
                    <tbody>
                        <tr>
                    <p>Username and Password will be automatic generated.</p>
                        </tr>
                        <tr>
                            <td><label>First Name </label></td>
                            <td><input type="text" name="firstName"/></td>
                        </tr>
                        <tr>
                            <td><label>Last Name </label></td>
                            <td><input type="text" name="lastName"/></td>
                        </tr>
                        <tr>
                            <td><label>D.O.B </label></td>
                            <td><input type="date" name="dob"/></td>
                        </tr>
                        <tr>
                            <td><label>Address </label></td>
                            <td><input type="text" name="address"/></td>
                        </tr>
                        <tr>
                            <td><label></label></td>
                            <td><input type="submit" value="Signup" class="btn"/></td>
                        </tr>
                    </tbody>
                </table>
            </form>
            <br>
            <a href="login-page.jsp">Back to Login</a>
        </div>
    </body>
</html>
