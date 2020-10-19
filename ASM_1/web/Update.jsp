<%-- 
    Document   : Update
    Created on : Mar 1, 2020, 6:16:58 PM
    Author     : PauL
--%>

<%@page import="haudq.dtos.EmplDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <% EmplDTO x = (EmplDTO) request.getAttribute("INFO");%>
        <form action="MainController" method="POST">
            <table border="1">
                <thead>
                    <tr>
                        <th>EmployeeID</th>
                        <th><input type="text" name="txtEmpID" value="<%= x.getEmployeeID()%>" readonly="true"/></th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>Password</td>
                        <td>*********</td>
                    </tr>
                    <tr>
                        <td>Employee Name</td>
                        <td><input type="text" name="txtEmpName" value="<%= x.getEmployeeName()%>"/></td>
                    </tr>
                    <tr>
                        <td>Address</td>
                        <td><input type="text" name="txtAddress" value="<%= x.getAddress()%>"/></td>
                    </tr>
                </tbody>
            </table>
                    <input type="submit" name="action" value="Update"/>

        </form>
    </body>
</html>
