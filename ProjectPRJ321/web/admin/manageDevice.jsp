<%-- 
    Document   : manageDevice
    Created on : Mar 12, 2020, 9:31:08 AM
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
        <h1>Manage Device Page!</h1>  
        <a href="MainController?action=Logout">Login out</a><br/><br/>
        <a href="admin/createDevice.jsp">Create new Device</a><br/>
        <a href="admin/begin.jsp">return</a><br/>
        <h3><font color="green">${requestScope.SUCC}</font></h3><br/>
      
        <form action="AdminController" method="POST">
            <h2> Device name: <input type="text" name="txtSearch" /> <input type="submit" name="action" value="Search Device"/></h2><br/>
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
                    <th>Location</th>
                    <th>Delete</th>
                    <th>Update</th>
                    <th>History of device</th>
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
                    <td><a href="AdminController?action=DetailsImage&imgLink=<%= d.getImage()%>&id=<%=d.getDeviceID()%>&name=<%=d.getName()%>">Detail</a></td>
                    <td><%= d.getBuyDate()%></td>
                    <td><%= d.getWarrantyDate()%></td>
                    <td><a href="AdminController?action=DetailLocation&txtDeviceID=<%=d.getDeviceID()%>&txtName=<%=d.getName()%>">Details</a></td>
                    <td><a href="AdminController?action=DeleteDevice&id=<%= d.getDeviceID()%>&txtSearch=<%= request.getParameter("txtSearch")%>">Delete</a></td>
                    <td>
                        <form action="AdminController" method="POST">
                            <input type="hidden" name="txtDeviceID" value="<%= d.getDeviceID()%>"/>
                            <input type="submit" name="action" value="Update device"/>
                        </form>
                    </td>
                    <td><a href="AdminController?action=deviceHistory&deviceID=<%= d.getDeviceID()%>">details</a></td>
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
