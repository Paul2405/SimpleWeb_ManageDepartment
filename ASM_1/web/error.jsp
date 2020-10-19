<%-- 
    Document   : error
    Created on : Mar 1, 2020, 5:19:19 PM
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
        <font color="Red">
        <h2><%= request.getAttribute("ERROR")%></h2>
        <font/>
        <a href="index.jsp">Back to Login!</a>
    </body>
</html>
