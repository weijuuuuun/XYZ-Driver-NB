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
            <p>
                Registration Success. Your credentials below. <br />
                Please settle the annual fee. Claims can only be made 6 months after registration.
            </p>
            <p>Username: ${newUser.id} </p>
            <p>Password: ${newUser.password} </p>
            <p>Status: ${newUser.status} </p>
            <a href="login-page.jsp">Back to Login</a>
        </div>
    </body>
</html>
