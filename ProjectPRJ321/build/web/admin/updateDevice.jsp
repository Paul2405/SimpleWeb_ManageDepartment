<%-- 
    Document   : updateDevice
    Created on : Mar 21, 2020, 8:02:50 AM
    Author     : PauL
--%>

<%@page import="haudq.dtos.DeviceDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Update Device!</h1>
        <a href="admin/manageDevice.jsp">return.</a><br/><br/>
        <form action="AdminController" method="POST">
            <%
                DeviceDTO dto = (DeviceDTO) request.getAttribute("UPDEViCE");
                if (dto != null) {
            %>
            <table border="1">
                <thead>
                    <tr>
                        <th>Property</th>
                        <th>Details</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>DeviceID</td>
                        <td><input type="text" name="txtDeviceID" value="<%= dto.getDeviceID()%>" readonly="true"/></td>
                    </tr>
                    <tr>
                        <td>Name</td>
                        <td><input type="text" name="txtName" value="<%= dto.getName()%>"/><font color="red">${requestScope.INVALID.deviceNameError}</font></td>
                    </tr>
                    <tr>
                        <td>Description</td>
                        <td><input type="text" name="txtDescription" value="<%= dto.getDecription()%>"/><font color="red">${requestScope.INVALID.deviceDescriptionError}</font></td>
                    </tr>
                    <tr>
                        <td>Type</td>
                        <td><input type="text" name="txtType" value="<%= dto.getType()%>"/><font color="red">${requestScope.INVALID.deviceTyoeError}</font></td>
                    </tr>
                    <tr>
                        <td>Buy date</td>
                        <td><input type="text" name="txtBuyDate" value="<%= dto.getBuyDate()%>"/><font color="red">${requestScope.INVALID.deviceBuyError}</font></td>
                    </tr><tr>
                        <td>Warranty date</td>
                        <td><input type="text" name="txtWarranty" value="<%= dto.getWarrantyDate()%>"/><font color="red">${requestScope.INVALID.deviceWarrantyError}</font></td>
                    </tr>
                </tbody>
            </table>
            <br/><input type="submit" name="action" value="Save update"/>
            <%
            } else {
            %>
            No record found!
            <%
                }
            %>
        </form>
    </body>
</html>
