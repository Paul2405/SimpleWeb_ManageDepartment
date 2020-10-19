<%-- 
    Document   : index
    Created on : Mar 9, 2020, 3:54:42 PM
    Author     : PauL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
    </head>
    <body>
        <h1>Login Page!</h1>
        <form action="MainController" method="POST">
            Username: <input type="text" name="txtUsername"/> &nbsp; <font color="red"> ${requestScope.INVALID.usernameError}</font><br/>
            Password: <input type="password" name="txtPassword"/> &nbsp;<font color="red"> ${requestScope.INVALID.passwordError}<font/><br/>
            <input type="submit" name="action" value="Login"/>
        </form>
    </body>
</html>
