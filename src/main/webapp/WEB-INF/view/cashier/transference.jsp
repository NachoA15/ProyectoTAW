<%@ page import="es.uma.proyectotaw.dto.client.Client_ClientDTO" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: manuj
  Date: 04/05/2023
  Time: 20:27
  To change this template use File | Settings | File Templates.
--%>

<%-- Author: Manuel Jesús Jerez --%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Client_ClientDTO client = (Client_ClientDTO) request.getAttribute("client");
%>

<html>
<head>
    <title>Transference</title>
</head>
<body>

    <h3><a href="/atm/<%=client.getId()%>">Back</a></h3>

    <h2>Enter the transfer details: </h2>

    <c:if test="${error != null}">
        <p style="color: red">
                ${error}
        </p>
    </c:if>

<form:form action="/transferenceATM/save" modelAttribute="operation" method="post">
    <form:hidden path="client"/>
    <form:hidden path="origin"/>
    Amount:
    <form:input path="amount"/>
    <br/><br/>
    Destination account:
    <form:select path="destination">
        <form:options items="${accounts}" itemLabel="iban" itemValue="id"/>
    </form:select>
    <br/><br/>
    Payment:
    <form:select path="payment">
        <form:options items="${currencys}"/>
    </form:select>
    <br/><br/>
    <form:button>OK</form:button>
</form:form>

</body>
</html>
