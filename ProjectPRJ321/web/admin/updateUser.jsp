<%-- 
    Document   : updateUser
    Created on : Mar 9, 2020, 9:51:56 PM
    Author     : PauL
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="haudq.dtos.UserErrorDTO"%>
<%@page import="haudq.dtos.UserAppDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>updateUser Page</title>
    </head>
    <body>
        <h1>Update User Page</h1>
        <a href="index.jsp">return!</a>
        <form action="AdminController" method="POST">
            <%
                UserAppDTO data = (UserAppDTO) request.getAttribute("DATA");
                List<String> listRoomID = (List<String>) request.getAttribute("LIST");
                List<String> roleList = new ArrayList<>();
                String role = data.getRole().trim();
                String roomID = data.getRoomID().trim();
                roleList.add("admin");
                roleList.add("user");
                roleList.add("staff");
                for (int i = 0; i < roleList.size(); i++) {
                        if(role.equals(roleList.get(i))){
                            roleList.remove(i);
                        }
                    }
            %>
            Username: <input type="text" name="txtUsername" value="<%= data.getUsername()%>" readonly="true"/><br/>
            Fullname: <input type="text" name="txtFullname" value="<%=data.getFullname()%>"/><font color="red">${requestScope.ERROR.fullnameError}</font><br/>
            Role: <select name="listRole">
                <option value="<%=role%>"><%=role%></option>
                <option value="<%= roleList.get(0).toString()%>"><%= roleList.get(0).toString()%></option>
                <option value="<%= roleList.get(1).toString()%>"><%= roleList.get(1).toString()%></option>
            </select><br/>
            RoomID:<select name="listRoomID">
                <option value="<%= roomID%>"><%= roomID%></option>
                <%
                    if (listRoomID != null) {
                        if (!listRoomID.isEmpty()) {
                            for (int i = 0; i < listRoomID.size(); i++) {
                                if (roomID.equals(listRoomID.get(i).toString())) {
                                    listRoomID.remove(i);
                                }
                            }
                            for (String x : listRoomID) {
                %>
                <option value="<%=x%>"><%=x%></option>
                <%
                            }
                        }
                    }
                %>
            </select><br/>
            <input type="hidden" name="oldRoom" value="<%=roomID%>"/>
            <input type="hidden" name="txtSearch" value="<%= request.getParameter("txtSearch")%>"/>
            <input type="hidden" name="txtRole" value="<%= request.getParameter("txtRole")%>"/>
            <input type="submit" name="action" value="Update data"/>
        </form>
    </body>
</html>
