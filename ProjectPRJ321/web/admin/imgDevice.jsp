<%-- 
    Document   : imgDevice
    Created on : Mar 17, 2020, 4:46:52 PM
    Author     : PauL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Image of device ${sessionScope.IMG.name}</h1>
        <a href="admin/manageDevice.jsp">return.</a><br/><br/>
        <img src="http://localhost:8080/Image/${sessionScope.IMG.image}" width="400" height="300" style="margin: 0"/><br/><br/>
        <form action="GetImageController" method="POST" enctype="multipart/form-data">
            <h3>Update image:<input type="file" name="txtImage" onchange="this.form.submit()"/></h3>
        </form>
        <input type="hidden" name="imgLink" value="${param.imgLink}"/>

        <%
            String imaURL = (String) request.getAttribute("imgLink");
            if (imaURL != null) {
                if (imaURL.length() != 0) {
        %>

        <img src="http://localhost:8080/Image/<%=imaURL%>" width="400" height="300" style="margin: 0"/><br/>
        <form action="AdminController" method="POST">
            <input type="hidden" name="txtImage" value="<%=imaURL%>"/>
            <input type="hidden" name="txtDeviceID" value="${sessionScope.IMG.deviceID}"/>
            <input type="submit" name="action" value="Update image"/>
        </form>
        <form action="admin/manageDevice.jsp" method="POST">
            <input type="submit" name="action" value="Cancel"/>
        </form>
        <%
            }
        } else {
        %>

        <font color="red">No file load</font>
        <%
            }
        %>

        <br/>
    </body>
</html>
