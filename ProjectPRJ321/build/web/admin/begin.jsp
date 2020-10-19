<%-- 
    Document   : begin
    Created on : Mar 9, 2020, 4:56:52 PM
    Author     : PauL
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ADMIN Page</title>
    </head>
    <body>
        <h1>Welome user ${sessionScope.USERLOGIN}</h1><br/>
        <a href="MainController?action=Logout">Login out</a><br/><br/>
        <h3>
            <a href="admin/manageUserRole.jsp?txtRole=user">Manage User</a><br/><br/>
            <a href="admin/manageUserRole.jsp?txtRole=staff">Manage Staff</a><br/><br/>
            <a href="admin/manageDevice.jsp">Manage Device</a><br/><br/>
            <a href="admin/manageRoom.jsp">Manage Room</a><br/><br/>
            <a href="AdminController?action=addUserAndDeviceToRoom">Add user and device to room</a><br/><br/>
            <a href="AdminController?action=notify">Notification</a><br/><br/>
        </h3>
    </body>
</html>
