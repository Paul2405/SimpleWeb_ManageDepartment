<%-- 
    Document   : admin
    Created on : Mar 1, 2020, 5:02:12 PM
    Author     : PauL
--%>

<%@page import="haudq.dtos.EmplDTO"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Welcome employee management pages!</h1>
        <%
            List<EmplDTO> dto = (List<EmplDTO>) request.getAttribute("DATA");
            if (dto != null) {

        %>
        <table border="1">
            <thead>
                <tr>
                    <th>EmployeeID</th>
                    <th>Employee Name</th>
                    <th>Address</th>
                    <th>Update</th>
                </tr>
            </thead>
            <tbody>
                <% for (EmplDTO x : dto) {%>
                <tr>
                    <td><%= x.getEmployeeID()%></td>
                    <td><%= x.getEmployeeName()%></td>
                    <td><%= x.getAddress()%></td>
                    <td>
                        <a href="MainController?action=Edit&id=<%= x.getEmployeeID()%>">Update</a>
                    </td>
                </tr>
                <%}%>
            </tbody>
        </table>
        <%
            }
        %>


    </body>
</html>
