<%@ page import="es.uma.proyectotaw.dto.management.FullClientDTO" %><%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 28/04/2023
  Time: 14:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    /**
     * @author: Ignacio Alba
     */
%>
<%
  FullClientDTO client = (FullClientDTO) request.getAttribute("client");
%>
<html>
<head>
    <title>Operations by <%=client.getIdentificationNumber()%></title>
</head>
<body>
  <h1>Operation history for client with id <%=client.getIdentificationNumber()%></h1>

  <h3>Account information</h3>

    <table>
        <tr>
            <td><b>IBAN:</b></td>
            <td><%=client.getAccount().getIban()%></td>
        </tr>
        <tr>
            <td><b>Opening date:</b></td>
            <td><%=client.getAccount().getOpeningDate()%></td>
        </tr>
        <tr>
            <td><b>Balance:</b></td>
            <td><%=client.getAccount().getBalance()%></td>
        </tr>
        <tr>
            <td><b>Currency:</b></td>
            <td><%=client.getAccount().getCurrency()%></td>
        </tr>
        <tr>
            <td><b>Status:</b></td>
            <td><%=client.getAccount().getStatus()%></td>
        </tr>
    </table>

    <hr/>
</body>
</html>
