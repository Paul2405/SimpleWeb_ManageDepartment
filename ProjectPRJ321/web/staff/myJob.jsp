<%-- 
    Document   : myJob
    Created on : Mar 22, 2020, 7:05:05 PM
    Author     : PauL
--%>

<%@page import="java.util.List"%>
<%@page import="haudq.dtos.HistoryDeviceDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Your job</h1>
        <a href="GetDeviceToStaffController">return.</a><br/><br/>
        <%
            List<HistoryDeviceDTO> list = (List<HistoryDeviceDTO>) request.getAttribute("MYJOB");
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
                    <th>Receive date</th>
                    <th>Finish repair</th>
                    <th>Cancel</th>
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
                    <td><%= r.getStartTime()%></td>
                    <td><a href="staff/finish.jsp?deviceID=<%= r.getDeviceID()%>">Finish</a></td>
                    <td><a href="StaffController?action=cancel&deviceID=<%= r.getDeviceID()%>">Cancel</a></td>
                </tr>
                <%
                    }
                %>
            </tbody>
        </table>

        <%                } else {
        %>
        Nothing to do!
        <%
                }
            }
        %>
    </body>
</html>
