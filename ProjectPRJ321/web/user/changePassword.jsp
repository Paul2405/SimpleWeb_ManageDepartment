<%-- 
    Document   : changePassword
    Created on : Mar 22, 2020, 12:05:51 PM
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
        <h1>Change password page</h1><br/>
        <a href="MainController?action=Logout">Login out</a><br/><br/>
        <a href="GetDeviceInRoomUserController">return</a><br/><br/>
        <form action="UserController" method="POST">
            Old password: <input type="password" name="txtOldPass"/><font color="red">${requestScope.INVALID.passwordError}</font><br/>
            New password: <input type="password" name="txtNewPass"/><font color="red">${requestScope.INVALID.roomIDError}</font><br/>
            Confirm password: <input type="password" name="txtConfPass"/><font color="red">${requestScope.INVALID.confirmPassError}</font><br/>
            <input type="submit" name="action" value="Change password"/>
        </form>
    </body>
</html>
