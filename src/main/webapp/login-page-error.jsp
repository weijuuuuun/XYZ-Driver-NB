<!DOCTYPE html>
<html>
    <head>
        <title>Login</title>
        <link type="text/css" rel="stylesheet" href="css/style.css">
        <link type="text/css" rel="stylesheet" href="css/login-style.css">
        
    </head>
    <body>
        <div id="wrapper">
            <div id="header">
                <h2>XYZ Driver Association (Member)</h2>
                <a href="${pageContext.request.contextPath}/admin-login-page.jsp">Log in as Admin</a>
            </div>
        </div>
        <div id="container">
            <h3>Login</h3>
            <!-- we only link to jsp now for testing -->
            <form action="${pageContext.request.contextPath}/authenticate-member" method="POST">
                <table>
                    <tbody>
                        <tr>
                            <td><label>User ID: </label></td>
                            <td><input type="text" name="userId"/></td>
                        </tr>
                        <tr>
                            <td><label>Password: </label></td>
                            <td><input type="password" name="password"/></td>
                        </tr>
                        <tr>
                            <td><label></label></td>
                            <td><input type="submit" value="Login" class="btn"/></td>
                        </tr>
                    </tbody>
                </table>

                <p style="color:red;">Wrong username or password</p>
            </form>
            <br>
            <!--note: we don't link to jsp, we link to controller class-->
            <a href="signup-page.jsp">Sign Up</a>
        </div>
    </body>
</html>
