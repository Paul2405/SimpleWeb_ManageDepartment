<%-- 
    Document   : history
    Created on : Mar 22, 2020, 2:04:53 PM
    Author     : PauL
--%>

<%@page import="haudq.dtos.HistoryDeviceDTO"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>History of device</h1>
        <a href="admin/manageDevice.jsp">return</a><br/><br/>
        <%
            List<HistoryDeviceDTO> dto = (List<HistoryDeviceDTO>) request.getAttribute("HISTORY");
            if (dto != null) {
                if (!dto.isEmpty()) {
        %>
        <table border="1">
            <thead>
                <tr>
                    <th>NO</th>
                    <th>Time of request</th>
                    <th>Content of request</th>
                    <th>User request</th>
                    <th>Time of end repair</th>
                    <th>Content repair</th>
                    <th>Result</th>
                    <th>User repair</th>
                </tr>
            </thead>
            <tbody>
                <%
                    int count = 0;
                    for (HistoryDeviceDTO h : dto) {
                %>
                <tr>
                    <td><%= count++%></td>
                    <td><%= h.getTimeRequest()%></td>
                    <td><%= h.getFixConttent()%></td>
                    <td><%=h.getUserRequest()%></td>
                    <td><%=h.getEndTime()%></td>
                    <td><%= h.getRepairConttent()%></td>
                    <td>
                        <%
                            if (!h.isIsGetRequest()) {
                        %>
                        requesting
                        <%
                        } else if (h.isIsGetRequest() && !h.isResultRepair()) {
                        %>
                        repairing
                        <%
                        } else if (h.isIsGetRequest() && h.isResultRepair()) {
                        %>
                        repaired
                        <%
                            }
                        %>
                    </td>
                    <td><%=h.getUserRepair()%></td>
                </tr>
                <%
                    }
                %>
            </tbody>
        </table>
        <%
        } else {
        %>
        <h3><font color="greean">Device is new and no repair anytime before.</font></h3>
            <%
                    }
                }
            %>
        Number of repairs: ${requestScope.NUMBER}
    </body>
</html>
