<%-- 
    Document   : locationDevice
    Created on : Mar 19, 2020, 3:54:50 PM
    Author     : PauL
--%>

<%@page import="java.util.List"%>
<%@page import="haudq.dao.ManageDAO"%>
<%@page import="haudq.dtos.LocationDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1><font color="blue">Location of device: ${sessionScope.NAME}!</font></h1><br/>
        <a href="admin/manageDevice.jsp">return.</a><br/><br/>

        <form action="AdminController" method="POST">
            Device has been in: ${requestScope.CURRENT.roomID} at ${requestScope.CURRENT.changeDate} <br/>
            <%
                LocationDTO local = (LocationDTO) request.getAttribute("OLD");
                if (local != null) {
            %>
            <h2> <font color="blue"> History of location:</font> </h2><br/>
            <h3>
                -Used to be in roomID : <%= local.getRoomID()%> at <%= local.getChangeDate()%><br/>
                -Move to roomID:  ${requestScope.CURRENT.roomID} by ${requestScope.CURRENT.username}<br/>
                -Reason: <%=local.getReason()%><br/><br/>
                <input type="hidden" name="txtOldRoomID" value="<%= local.getRoomID()%>"/>
                <%
                } else {
                %>
                <font color="red">That device has not change location!</font><br/><br/>
                <input type="hidden" name="txtOldRoomID" value=""/>
                <%
                    }
                %>
            </h3> 
            Change location to roomID: 
            <%
                List<String> room = (List<String>) request.getAttribute("ROOM");
                if (room != null) {
                    if (!room.isEmpty()) {
            %>
            <select name="ListRoomID">
                <%
                    for (String x : room) {
                %>
                <option value="<%=x%>"><%=x%></option>
                <%
                    }%>
            </select>
            <%
                    }
                }
            %>
            <input type="hidden" name="txtCurRoomID" value="${requestScope.CURRENT.roomID}"/>
            <input type="hidden" name="txtDeviceID" value="${requestScope.CURRENT.deviceID}"/>
            <input type="hidden" name="txtDateCur" value="${requestScope.CURRENT.changeDate}"/>
            <br/>
            Reason change: <input type="text" name="txtReason"/><br/>
            <input type="submit" name="action" value="Change"/> <br/>
        </form>
    </body>
</html>
