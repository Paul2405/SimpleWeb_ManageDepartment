<%-- 
    Document   : notify
    Created on : Mar 22, 2020, 7:57:24 PM
    Author     : PauL
--%>

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
        <a href="admin/begin.jsp">return.</a><br/><br/>
        <a href="NotifyAdminController">refresh</a><br/><br/>
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
    </body>
</html>
