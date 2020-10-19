<%-- 
    Document   : chooseRoom
    Created on : Mar 21, 2020, 4:25:40 PM
    Author     : PauL
--%>

<%@page import="haudq.dtos.RoomDTO"%>
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
        <h2>Choose room to add user and device:</h2>
        <a href="admin/begin.jsp">Return.</a>
        <%
            List<RoomDTO> listRoom = (List<RoomDTO>) request.getAttribute("LISTROOM");
            if (listRoom != null) {
                if (!listRoom.isEmpty()) {
        %>
        <table border="1">
            <thead>
                <tr>
                    <th>No</th>
                    <th>RoomID</th>
                    <th>Room Name</th>
                    <th>Status</th>
                    <th>Permanently deleted</th>
                    <th>Choose</th>
                </tr>
            </thead>
            <tbody>
                <% int count = 0;
                    for (RoomDTO room : listRoom) {%>
                <tr>
                    <td><%= count++%></td>
                    <td><%= room.getRoomID()%></td>
                    <td><%= room.getRoomName()%></td>
                    <td>
                        <%if (room.isStatus()) {%>
                        Not use
                        <%} else {%>
                        Used
                        <%}%>
                    </td>
                    <td><a href="RealDeleteRoomController?roomID=<%= room.getRoomID()%>">Delete</a></td>
                    <td><a href="SelectUserToAddController?roomID=<%= room.getRoomID()%>&status=<%= room.isStatus()%>">Choose</a></td>
                </tr>
                <%}%>
            </tbody>
        </table>

        <%
        } else {
        %>
        <font color ="red"> No found!</font>
        <%
                }
            }
        %>
    </body>
</html>
