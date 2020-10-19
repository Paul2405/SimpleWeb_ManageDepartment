<%-- 
    Document   : error
    Created on : Mar 9, 2020, 4:21:39 PM
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
        <h1><font color="blue">Sorry!</font></h1>
        <h3><font color="red">${requestScope.ERROR}</font></h3>
        <a href="index.jsp">return!</a>
    </body>
</html>
