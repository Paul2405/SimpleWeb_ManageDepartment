<%-- 
    Document   : finish
    Created on : Mar 22, 2020, 7:12:32 PM
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
        <h1>Finish repair</h1>
        <a href="MainController?action=Logout">Login out</a><br/><br/>
        <a href="GetDeviceToStaffController">return</a><br/><br/>
        <form action="StaffController" method="POST">
            <h3>  Content repair</h3>
            <input type="hidden" name="deviceID" value="${param.deviceID}"/>
            <input type="text" name="txtContent"/>
            <input type="submit" name="action" value="Finish"/>
        </form>
        <font color="red">${requestScope.INVALID}</font>
    </body>
</html>
