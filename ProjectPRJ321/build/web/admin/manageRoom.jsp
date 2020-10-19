<%-- 
    Document   : manageRoom
    Created on : Mar 21, 2020, 10:40:53 AM
    Author     : PauL
--%>

<%@page import="java.util.List"%>
<%@page import="haudq.dtos.RoomDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Manage Room page!</h1>
        <a href="MainController?action=Logout">Login out</a><br/><br/>
        <a href="admin/createRoom.jsp">Create new Room</a><br/>
        <a href="admin/begin.jsp">return</a><br/>
        <h3><font color="green">${requestScope.SUCC}</font></h3><br/>
        <form action="AdminController" method="POST">
            <h2> Room name: <input type="text" name="txtSearch" /> <input type="submit" name="action" value="Search Room"/></h2><br/>
        </form>

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
                    <th>Delete</th>
                    <th>Update</th>
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
                        <%}else{%>
                        Used
                        <%}%>
                    </td>
                    <td><a href="DeleteRoomController?id=<%= room.getRoomID()%>">Delete</a></td>
                    <td>
                        <form action="admin/updateRoom.jsp" method="POST">
                            <input type="hidden" name="txtRoomID" value="<%= room.getRoomID()%>"/>
                            <input type="hidden" name="txtRoomName" value="<%= room.getRoomName()%>"/>
                            <input type="submit" value="Update"/>
                        </form>
                    </td>
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
