<%@ page import="es.uma.proyectotaw.dto.UserDTO" %>
<%@ page import="es.uma.proyectotaw.dto.client.Client_ClientDTO" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- Author: Manuel Jesús Jerez Sánchez --%>

<%--
  Created by IntelliJ IDEA.
  User: manuj
  Date: 24/04/2023
  Time: 18:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Client_ClientDTO client = (Client_ClientDTO) request.getAttribute("client");
%>

<html>
<head>
    <title>TakeMoney</title>
</head>
<body>
    <h3><a href="/atm/<%=client.getId()%>">Back</a></h3>
    <h2> Indicate the amount of money to take out: </h2>

    <c:if test="${error != null}">
        <p style="color: red">
            ${error}
        </p>
    </c:if>

    <form:form action="/takeMoneyATM/save" method="post" modelAttribute="operation">
        <form:hidden path="client"/>
        <form:hidden path="origin"/>
        <form:hidden path="destination"/>
        Amount: <form:input path="amount" value=""/><br/>
        <br/>
        <form:button>OK</form:button>
    </form:form>

</body>
</html>
