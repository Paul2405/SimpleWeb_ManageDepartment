<%-- 
    Document   : notify
    Created on : Mar 22, 2020, 10:10:58 PM
    Author     : PauL
--%>

<%@page import="haudq.dtos.NotificationDTO"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Notification!</h1>
        <a href="MainController?action=Logout">Login out</a><br/><br/>
        <a href="GetDeviceInRoomUserController">return</a><br/><br/>
        <a href="GetNotificationController">refresh</a><br/><br/>
        <%
            List<NotificationDTO> notify = (List<NotificationDTO>) request.getAttribute("NOTIFY");
            if (notify != null) {
                if (!notify.isEmpty()) {
        %>
        <table border="1">
            <thead>
                <tr>
                    <th>No</th>
                    <th>Content</th>
                    <th>Operation</th>
                </tr>
            </thead>
            <tbody>
                <%
                    int count = 0;
                    for (NotificationDTO n : notify) {
                %>
                <tr>
                    <td><%= count++%></td>
                    <td><%= n.getContent()%></td>
                    <td><a href="GetNotifyController?id=<%= n.getNotifyID()%>">Seen</a></td>
                </tr>
                <%
                    }
                %>
            </tbody>
        </table>

        <%
        } else {
        %>
        No notification!
        <%
                }
            }
        %>
    </body>
</html>
