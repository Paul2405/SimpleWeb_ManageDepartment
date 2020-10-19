<%-- 
    Document   : createRoom
    Created on : Mar 21, 2020, 12:26:28 PM
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
        <h1>Create Room page</h1>
         <a href="admin/manageRoom.jsp">return</a><br/>
        <form action="AdminController" method="POST">
            RoomID: <input type="text" name="txtRoomID"/><font color="red">${requestScope.INVALID.roomIDError}</font><br/>
            Room Name: <input type="text" name="txtRoomName"/><font color="red">${requestScope.INVALID.roomNameError}</font><br/>
            <input type="submit" name="action" value="Create room"/>
        </form>
    </body>
</html>
