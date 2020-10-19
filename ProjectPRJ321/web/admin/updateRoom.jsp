<%-- 
    Document   : updateRoom
    Created on : Mar 21, 2020, 2:54:47 PM
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
        <h1>Update Room!</h1>
        <form action="AdminController" method="POST">
            RoomID: <input type="text" name="txtRoomID" value="${param.txtRoomID}" readonly="true"/><br/>
            Room name:<input type="text" name="txtRoomName" value="${param.txtRoomName}"/><font color="red">${requestScope.INVALID.roomNameError}</font><br/>
            <input type="submit" name="action" value="Update room"/>
        </form>
    </body>
</html>
