<%-- 
    Document   : request
    Created on : Mar 22, 2020, 2:48:39 PM
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
        <h1>Request to repair device ${param.txtDeviceName}</h1><br/>
        <a href="MainController?action=Logout">Login out</a><br/><br/>
        <a href="GetDeviceInRoomUserController">return</a><br/><br/>
        <form action="UserController" method="POST">
            <h3>  Content request:</h3>
            <input type="hidden" name="deviceID" value="${param.txtDeviceID}"/>
            <input type="text" name="txtContent"/>
            <input type="submit" name="action" value="Request to repair"/>
        </form>
        <font color="red">${requestScope.INVALID}</font>
    </body>
</html>
