<%-- 
    Document   : createDecice
    Created on : Mar 12, 2020, 2:32:29 PM
    Author     : PauL
--%>

<%@page import="java.util.List"%>
<%@page import="haudq.dao.ManageDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Create new Device page!</h1>
        <br/><br/>
        <a href="admin/manageDevice.jsp">return</a><br/><br/>
        

        <form action="AdminController" method="POST">
            DeviceID: <input type="text" name="txtDeviceID" value="${requestScope.REDATA.deviceID}"/><font color="red">${requestScope.INVALID.deviceIDError}</font><br/><br/>
           
            Name: <input type="text" name="txtName" value="${requestScope.REDATA.name}"/><font color="red" >${requestScope.INVALID.deviceNameError}</font><br/><br/>
            Description: <input type="text" name="txtDescription" value="${requestScope.REDATA.decription}"/><font color="red">${requestScope.INVALID.deviceDescriptionError}</font><br/><br/>
            Type: <input type="text" name="txtType" value="${requestScope.REDATA.type}"/><font color="red" >${requestScope.INVALID.deviceTyoeError}</font><br/><br/>
            Buy date: <input type="text" name="txtBuyDate" value="${requestScope.REDATA.buyDate}"/><font color="red">${requestScope.INVALID.deviceBuyError}</font><br/><br/>
            Warranty date: <input type="text" name="txtWarrantyDate" value="${requestScope.REDATA.warrantyDate}"/><font color="red">${requestScope.INVALID.deviceWarrantyError}</font><br/><br/>
            <input type="submit" name="action" value="Create device"/>
        </form>


    </body>
</html>
