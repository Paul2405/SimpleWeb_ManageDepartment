<%-- 
    Document   : chooseUser
    Created on : Mar 21, 2020, 4:43:13 PM
    Author     : PauL
--%>

<%@page import="haudq.dtos.UserAppDTO"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Add user and device to room page!</h1>
        <a href="SelectRoomToAddController">Return.</a>
        <%
            List<UserAppDTO> data = (List<UserAppDTO>) request.getAttribute("LISTUSER");
            if (data != null) {
                if (!data.isEmpty()) {
        %>
        <h2>Choose user to add into room and manage device:</h2>
        <table border="1">
            <thead>
                <tr>
                    <th>No</th>
                    <th>Username</th>
                    <th>Fullname</th>
                    <th>Role</th>
                    <th>Add to roomID</th>
                    <th>Choose</th>
                </tr>
            </thead>
            <tbody>
                <%                    int count = 0;
                    for (UserAppDTO x : data) {
                %>
                <tr>
                    <td><%= count++%></td>
                    <td><%= x.getUsername()%></td>
                    <td><%= x.getFullname()%></td>
                    <td><%= x.getRole()%></td>
                    <td><%= x.getRoomID()%></td>
                    <td>
                        <a href="SelectDeviceToAddController?roomID=<%= x.getRoomID()%>&userID=<%= x.getUsername()%>">Choose</a>
                    </td>
                </tr>
                <%
                    }
                %>
            </tbody>
        </table>
        <%
        } else {%>
        No found
        <%}
        } else {
        %>
        <br/><a href="SelectDeviceToAddController?roomID=${requestScope.roomID}&userID=${requestScope.userID}&status=false">Next to choose device</a>
        <%}%>
    </body>
</html>
