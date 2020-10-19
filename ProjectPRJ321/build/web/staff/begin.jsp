<%-- 
    Document   : begin
    Created on : Mar 9, 2020, 4:57:22 PM
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
        <h1>Welome user ${sessionScope.USERLOGIN}</h1><br/>
        
        <a href="MainController?action=Logout">Login out</a><br/><br/>
        <a href="StaffController?action=notify">Notification</a><br/>
        <a href="StaffController?action=myJob">My job</a>
        <form action="StaffController" method="POST">
            <h3>Device name: <input type="text" name="txtSearch"/>
                <input type="submit" name="action" value="Search"/><br/></h3>
        </form>
        <%
            List<DeviceDTO> dto = (List<DeviceDTO>) request.getAttribute("DEVICE");
            if (dto != null) {
                if (!dto.isEmpty()) {
        %>
        <table border="1" cellpadding="1">
            <thead>
                <tr>
                    <th>No</th>
                    <th>DeviceID</th>
                    <th>Device name</th>
                    <th>Description</th>
                    <th>Type</th>
                    <th>Image</th>
                    <th>Buy Date</th>
                    <th>Warranty date</th>
                    <th>History of device</th>
                    <th>Location</th>
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
                    <td><img src="http://localhost:8080/Image/<%=d.getImage()%>" width="100" height="100"/></td>
                    <td><%= d.getBuyDate()%></td>
                    <td><%= d.getWarrantyDate()%></td>
                    <td><a href="StaffController?action=history&deviceID=<%= d.getDeviceID()%>">Details</a></td>
                    <td><a href="StaffController?action=detailLocation&txtDeviceID=<%=d.getDeviceID()%>&txtName=<%=d.getName()%>">Details</a></td>
                </tr>
                <%
                    }
                %>
            </tbody>
        </table>


        <%
        } else {
        %>
        <font color="red">No record found.</font>
        <%
                }
            }
        %>
    </body>
</html>
