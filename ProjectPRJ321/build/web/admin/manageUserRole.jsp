<%-- 
    Document   : manageUserRole
    Created on : Mar 9, 2020, 6:14:55 PM
    Author     : PauL
--%>

<%@page import="java.util.List"%>
<%@page import="haudq.dtos.UserAppDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>manageUserRole Page</title>
    </head>
    <body>
        <h1>Manage User page!</h1>
        <a href="MainController?action=Logout">Login out</a><br/><br/>
        <form action="AdminController" method="POSt">
            Fullname:  <input type="text" name="txtSearch"/> 
            <input type="hidden" name="txtRole" value="<%= request.getParameter("txtRole")%>"/>
            <input type="submit" name="action" value="Search"/><br/><br/>
        </form>
        <a href="admin/createUser.jsp?txtRole=<%= request.getParameter("txtRole")%>">Create new User</a><br/>
        <a href="index.jsp">return!</a><br/>
        <font color="red">${requestScope.SUCCESS}</font>

        <%
            List<UserAppDTO> data = (List<UserAppDTO>) request.getAttribute("DATA");
            if (data != null) {
                if (!data.isEmpty()) {
        %>
        <table border="1">
            <thead>
                <tr>
                    <th>No</th>
                    <th>Username</th>
                    <th>Fullname</th>
                    <th>Role</th>
                    <th>RoomID</th>
                    <th>Delete</th>
                    <th>Update</th>
                </tr>
            </thead>
            <tbody>
                <%                    int count = 0;
                    for (UserAppDTO x : data) {
                %>
                <tr>
                    <td><%= count++%></td>
                    <td><%= x.getUsername()%></td>
                    <td><%= x.getFullname()%></td>
                    <td><%= x.getRole()%></td>
                    <td><%= x.getRoomID()%></td>
                    <td>
                        <a href="AdminController?action=Delete&id=<%=x.getUsername()%>&txtSearch=<%=request.getParameter("txtSearch")%>&txtRole=<%= x.getRole()%>&txtRoomID=<%=x.getRoomID()%>">Delete</a>
                    </td>
                    <td>
                        <form action="AdminController" method="POST">
                            <input type="hidden" name="txtUserID" value="<%= x.getUsername()%>"/>
                            <input type="hidden" name="txtSearch" value="<%= request.getParameter("txtSearch")%>"/>
                            <input type="hidden" name="txtRole" value="<%= request.getParameter("txtRole")%>"/>
                            <input type="submit" name="action" value="Update"/>
                        </form>
                    </td>
                </tr>
                <%
                    }
                %>
            </tbody>
        </table>
        <%
        } else {%>
        <h3> <font color="red">Not Found!<font/></h3>
            <%}
                }
            %>

    </body>
</html>
