<%-- 
    Document   : createUser
    Created on : Mar 11, 2020, 7:21:32 PM
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
        <h1>Create new User!</h1>
        <a href="index.jsp">return!</a><br/>

        <form action="AdminController" method="POST">
            Username: <input type="text" name="txtUsername" />
            <font color="red">
            ${requestScope.NO.usernameError}
            </font>
            <br/>
            Fullname:  <input type="text" name="txtFullname" />
            <font color="red">
            ${requestScope.NO.fullnameError}
            </font><br/>
            Passowrd: <input type="password" name="txtPassword" /><font color="red">
            ${requestScope.NO.fullnameError}
            </font><br/>
            Confirm password: <input type="password" name="txtConfirmPass" />
            <font color="red">
            ${requestScope.NO.confirmPassError}
            </font>
            <br/>
            <input type="hidden" name="txtRole" value="<%=request.getParameter("txtRole")%>"/><br/>
           
            <input type="submit" name="action" value="Create"/>
        </form>

    </body>
</html>
