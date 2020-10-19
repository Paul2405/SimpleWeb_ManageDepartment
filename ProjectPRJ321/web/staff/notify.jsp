<%-- 
    Document   : notify
    Created on : Mar 22, 2020, 6:15:14 PM
    Author     : PauL
--%>

<%@page import="haudq.dtos.NotificationDTO"%>
<%@page import="haudq.dtos.HistoryDeviceDTO"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Notification page</h1>
        <a href="GetDeviceToStaffController">return.</a><br/><br/>
        <h3>About request repair device</h3>
        <a href="NotificationStaffController">refresh</a><br/><br/>
        <%
            List<HistoryDeviceDTO> list = (List<HistoryDeviceDTO>) request.getAttribute("LISTREQUEST");
            if (list != null) {
                if (!list.isEmpty()) {

        %>
        <table border="1">
            <thead>
                <tr>
                    <th>No</th>
                    <th>DeviceID</th>
                    <th>User request</th>
                    <th>Fix Content</th>
                    <th>Time request</th>
                    <th>Receive that request</th>
                </tr>
            </thead>
            <tbody>
                <%                    int count = 0;
                    for (HistoryDeviceDTO r : list) {
                %>
                <tr>
                    <td><%=count++%></td>
                    <td><%= r.getDeviceID()%></td>
                    <td><%= r.getUserRequest()%></td>
                    <td><%= r.getFixConttent()%></td>
                    <td><%= r.getTimeRequest()%></td>
                    <td><a href="StaffController?action=ReceiveRequest&deviceID=<%= r.getDeviceID()%>">Receive</a></td>
                </tr>
                <%
                    }
                %>
            </tbody>
        </table>

        <%                } else {
        %>
        No notification!
        <%
                }
            }
        %>
        <br/><br/>
        <h3>About your account</h3>
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
                    <td><a href="StaffGetNotifyController?id=<%= n.getNotifyID()%>">Seen</a></td>
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
