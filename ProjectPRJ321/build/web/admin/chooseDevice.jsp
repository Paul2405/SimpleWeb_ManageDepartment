<%-- 
    Document   : chooseDevice
    Created on : Mar 21, 2020, 5:05:08 PM
    Author     : PauL
--%>

<%@page import="haudq.dtos.DeviceDTO"%>
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
        <h2>Choose device to add into room and is managed by user:</h2>
        <a href="admin/begin.jsp">Return.</a>

        <%
            List<DeviceDTO> dto = (List<DeviceDTO>) request.getAttribute("LISTDEVICE");
            if (dto != null) {
                if (!dto.isEmpty()) {
        %>
        <form action="FinishAddController" method="POST">

            <table border="1" cellpadding="1">
                <thead>
                    <tr>
                        <th>No</th>
                        <th>DeviceID</th>
                        <th>Device name</th>
                        <th>Description</th>
                        <th>Type</th>
                        <th>Buy Date</th>
                        <th>Warranty date</th>
                        <th>Permanently deleted</th>
                        <th>Choose</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        int count = 0;
                        for (DeviceDTO d : dto) {
                    %>
                    <tr>
                        <td><%= count++%></td>
                        <td><%= d.getDeviceID()%></td>
                        <td><%= d.getName()%></td>
                        <td><%= d.getDecription()%></td>
                        <td><%= d.getType()%></td>
                        <td><%= d.getBuyDate()%></td>
                        <td><%= d.getWarrantyDate()%></td>
                        <td><a href="RealDeleteDeviceController?deviceID=<%= d.getDeviceID()%>&roomID=${param.roomID}&userID=${param.userID}&status=${param.status}">Delete</a></td>
                        <td>
                            <input type="checkbox" name="chkChoose" value="<%= d.getDeviceID()%>"/>
                            <input type="hidden" name="roomID" value="${param.roomID}"/>
                            <input type="hidden" name="userID" value="${param.userID}"/>
                        </td>
                    </tr>
                    <%
                        }
                    %>
                    <tr>
                <input type="hidden" name="status" value="${param.status}"/>
                <td><input type="submit" value="Finish add" </td>
                </tr>
                </tbody>
            </table>

        </form>
        <%
        } else {
        %>
        <font color="red">No device available.</font>
        <%
                }
            }
        %>

    </body>
</html>
