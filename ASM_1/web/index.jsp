<%-- 
    Document   : index
    Created on : Feb 18, 2020, 7:50:28 PM
    Author     : PauL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Login pages</h1>
        <form action="MainController" method="POST">
            Username: <input type="text" name="txtUsername"/><br/>
            Password: <input type="password" name="txtPassword"/><br/>
            <input type="submit" name="action" value="Login"/>
        </form>
    </body>
</html>
